package com.at.t.eCommerce.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {

	@Id
	@GeneratedValue
	@org.hibernate.annotations.UuidGenerator
	@Column(columnDefinition = "uuid", updatable = false, nullable = false)
	private UUID id;

	@NotBlank
	@Column(nullable = false)
	private String dno;

	@NotBlank
	@Column(nullable = false)
	private String street;

	@NotBlank
	@Column(nullable = false)
	private String city;

	@NotBlank
	@Column(nullable = false)
	private String state;

	@NotBlank
	@Column(nullable = false)
	private String country;

	@NotNull
	@Column(nullable = false)
	private Long pincode;

	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean isDefault;

//	check you may get error at the lazy time stamp at runtime
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id", nullable = false)
	private UserProfile profile;

}
