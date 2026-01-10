package com.at.t.eCommerce.service_impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.at.t.eCommerce.dto.request.AddressReqDTO;
import com.at.t.eCommerce.dto.request.Update_User_Req_DTO;
import com.at.t.eCommerce.dto.response.AddressResDTO;
import com.at.t.eCommerce.dto.response.Update_User_Res_DTO;
import com.at.t.eCommerce.exception.NotFoundException;
import com.at.t.eCommerce.mapper.UserMapper;
import com.at.t.eCommerce.model.AddressModel;
import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.model.UserProfile;
import com.at.t.eCommerce.repo.CoreUserRepo;
import com.at.t.eCommerce.service.UpdateUser;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class Update_User_Impl implements UpdateUser {

	private final CoreUserRepo userRepo;
	private final UserMapper userMapper;

	@Override
	@Transactional
	public Update_User_Res_DTO updateUser(Update_User_Req_DTO requestDTO) {

		log.info("Starting user update process for email: {}", requestDTO.getEmail());

		try {
			String email = requestDTO.getEmail();

			log.debug("Fetching user with email: {}", email);
			CoreUser user = userRepo.findByEmail(email).orElseThrow(() -> {
				log.error("User not found with email: {}", email);
				return new NotFoundException("User not found with email: " + email);
			});

			log.debug("User found with ID: {}, updating user details", user.getId());

			UserProfile profile = user.getProfile();
			if (profile == null) {
				profile = new UserProfile();
				profile.setUser(user);
				user.setProfile(profile);

			}

			if (requestDTO.getAddresses() != null) {

				for (AddressReqDTO addrDto : requestDTO.getAddresses()) {

					AddressModel addressEntity = userMapper.toAddressEntity(addrDto);

					addressEntity.setDefault(addrDto.getIsDefault() != null ? addrDto.getIsDefault() : false);

					addressEntity.setProfile(profile);
					profile.getAddresses().add(addressEntity);

				}

			}

			// Update core user fields
			userMapper.updateCoreUserFromDto(requestDTO, user);
			log.info("Core user fields updated from DTO");

			// Update user profile details
			userMapper.updateUserDetailsFromDto(requestDTO, user.getProfile());
			log.debug("User profile details updated from DTO");

			// Save updated user
			log.debug("Persisting updated user to database for userId: {}", user.getId());
			CoreUser updated = userRepo.save(user);

			log.info("User updated successfully for email: {}, userId: {}", email, updated.getId());

			// Map to response DTO
			Update_User_Res_DTO response = userMapper.update_User_Res_DTO(updated);
			log.debug("Response DTO created for userId: {}", updated.getId());

			return response;

		} catch (NotFoundException e) {
			log.error("User not found during update process: {}", e.getMessage());
			throw e;

		} catch (Exception e) {
			log.error("Unexpected error occurred while updating user with email: {}. Error: {}", requestDTO.getEmail(),
					e.getMessage(), e);
			throw new RuntimeException("Failed to update user: " + e.getMessage(), e);
		}
	}
}