package com.at.t.eCommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.at.t.eCommerce.dto.request.AddressReqDTO;
import com.at.t.eCommerce.dto.request.Update_User_Req_DTO;
import com.at.t.eCommerce.dto.response.AddressResDTO;
import com.at.t.eCommerce.dto.response.Get_User_By_Email_Res_DTO;
import com.at.t.eCommerce.dto.response.Register_Response_DTO;
import com.at.t.eCommerce.dto.response.Update_User_Res_DTO;
import com.at.t.eCommerce.model.AddressModel;
import com.at.t.eCommerce.model.CoreUser;
import com.at.t.eCommerce.model.UserProfile;

@Mapper(componentModel = "spring")
public interface UserMapper {

	// ===== ENTITY → DTO (Response Mappings) =====

	Register_Response_DTO toRegisterResponse(CoreUser user);

	@Mapping(target = "fullName", source = "profile.fullName")
	@Mapping(target = "addresses", expression = "java(mapAddresses(user.getProfile().getAddresses()))")
	Update_User_Res_DTO update_User_Res_DTO(CoreUser user);
	
	AddressResDTO toAddressDTO(AddressModel model);
	
	default List<AddressResDTO> mapAddresses(List<AddressModel> list){
		
		return list.stream().map(this :: toAddressDTO).toList();
		
	}

	// GET THE USER =====

	@Mapping(target = "name", source = "profile.fullName", defaultValue = "N/A")
	Get_User_By_Email_Res_DTO toGetUserByEmailResponseDTO(CoreUser user);

	// ===== DTO → ENTITY (Request Mappings) =====

	void updateCoreUserFromDto(Update_User_Req_DTO dto, @MappingTarget CoreUser user);

	@Mapping(target = "addresses", ignore = true)
	void updateUserDetailsFromDto(Update_User_Req_DTO dto, @MappingTarget UserProfile userProfile);

	AddressModel toAddressEntity(AddressReqDTO dto);
	
}
