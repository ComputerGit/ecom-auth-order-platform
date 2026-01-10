package com.at.t.eCommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.at.t.eCommerce.dto.request.AuthenticationRequestDto;
import com.at.t.eCommerce.dto.request.Get_User_By_Email_Req_DTO;
import com.at.t.eCommerce.dto.request.Register_Request_DTO;
import com.at.t.eCommerce.dto.request.Update_User_Req_DTO;
import com.at.t.eCommerce.dto.response.AuthenticationResponse;
import com.at.t.eCommerce.dto.response.Get_User_By_Email_Res_DTO;
import com.at.t.eCommerce.dto.response.Register_Response_DTO;
import com.at.t.eCommerce.dto.response.Update_User_Res_DTO;
import com.at.t.eCommerce.exception.NotFoundException;
import com.at.t.eCommerce.service.LoginUser;
import com.at.t.eCommerce.service.RegisterUser;
import com.at.t.eCommerce.service.UpdateUser;
import com.at.t.eCommerce.service.user.GetUser;

import io.lettuce.core.dynamic.annotation.Param;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/account")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

	private final RegisterUser registerUser;
	private final LoginUser loginUser;
	private final UpdateUser updateUser;
	private final GetUser getUser;

	public record RefreshRequest(String accessToken, String refreshToken) {
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody Register_Request_DTO request) {
		try {

			Register_Response_DTO response = registerUser.registerUser(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (Exception e) {

			log.error("Error Registering User", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestDto requestDto) {
		log.info("Login attempt for user: {}", requestDto.getEmail());
		try {
			ResponseEntity<AuthenticationResponse> response = loginUser.requestDto(requestDto);
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUserProfile(@Valid @RequestBody Update_User_Req_DTO request) {
		try {

//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			String email = null;

//			if (authentication != null) {
//				email = authentication.getName();
//			} else {
//				return ResponseEntity.status(HttpStatus.LOCKED).body("YOU MUST LOG IN TO GET ACCESS TO UPDATE");
//			}

			Update_User_Res_DTO response = updateUser.updateUser(request);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}

//	 why we using valid here it checks at the updateuserreqdto validations if have any or any thing else
//	have to handle the global exceptions as industry
	@GetMapping("/retrieve")
	public ResponseEntity<?> getUserByEmail(@Valid @RequestBody Get_User_By_Email_Req_DTO request) {

		try {
			Get_User_By_Email_Res_DTO response = getUser.getUser(request);
			
			return ResponseEntity.ok(response);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
		}

	}
	
//	@DeleteMapping("/delete/?"param")
//	public ResponseEntity<T> deleteUserByEmail(@Valid @Param Delete_User_By_Email_DTO request){
//		
//		
//		
//	}

}
