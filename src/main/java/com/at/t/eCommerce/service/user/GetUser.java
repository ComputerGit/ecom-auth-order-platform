package com.at.t.eCommerce.service.user;


import com.at.t.eCommerce.dto.request.Get_User_By_Email_Req_DTO;
import com.at.t.eCommerce.dto.response.Get_User_By_Email_Res_DTO;

public interface GetUser {

 	public Get_User_By_Email_Res_DTO getUser(Get_User_By_Email_Req_DTO request);
	
 
}		

