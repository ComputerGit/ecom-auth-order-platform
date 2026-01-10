package com.at.t.eCommerce.service_impl;

import org.hibernate.service.spi.ServiceException;
import org.hibernate.tool.schema.internal.ExceptionHandlerCollectingImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.at.t.eCommerce.dto.request.Get_User_By_Email_Req_DTO;
import com.at.t.eCommerce.dto.response.Get_User_By_Email_Res_DTO;
import com.at.t.eCommerce.mapper.UserMapper;
import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.repo.CoreUserRepo;
import com.at.t.eCommerce.service.user.GetUser;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Service
public class Get_User_Impl implements GetUser {

	private final CoreUserRepo coreUserRepo;
	private final UserMapper userMapper;

	@Override
	public Get_User_By_Email_Res_DTO getUser(Get_User_By_Email_Req_DTO request) {

		try {
			String email = request.getEmail();
			if (email == null || email.isEmpty() || email.isBlank()) {
				throw new IllegalArgumentException(" User name is not Found");
			}

			CoreUser user = coreUserRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

			Get_User_By_Email_Res_DTO response = userMapper.toGetUserByEmailResponseDTO(user);

			return response;
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();

			throw new ServiceException("Service level Exception");
		}
	}

}
