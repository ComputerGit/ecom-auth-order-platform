package com.at.t.eCommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressResDTO {
	   private String dno;
	    private String street;
	    private String city;
	    private String state;
	    private String country;
	    private Long pincode;
	    private Boolean isDefault; // optional
}
