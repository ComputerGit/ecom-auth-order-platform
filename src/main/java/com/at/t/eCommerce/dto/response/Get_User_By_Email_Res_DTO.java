package com.at.t.eCommerce.dto.response;

import java.util.UUID;

import com.at.t.eCommerce.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Get_User_By_Email_Res_DTO {
	
	private UUID id;
	private String name;
	private String email;
    private Role role;

}
