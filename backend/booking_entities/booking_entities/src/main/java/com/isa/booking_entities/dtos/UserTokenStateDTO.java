package com.isa.booking_entities.dtos;

import java.time.LocalDateTime;

import com.isa.booking_entities.models.users.TypeOfUser;

public class UserTokenStateDTO {
	private String accessToken;
	private String email;
	private TypeOfUser typeOfUser;
	private Long expiresIn;
	private LocalDateTime lastTimePenaltyPointsReset;

	public UserTokenStateDTO() {
		this.accessToken = null;
		this.email = null;
		this.expiresIn = null;
		this.typeOfUser = null;
		this.lastTimePenaltyPointsReset= null;
	}

	public UserTokenStateDTO(String accessToken,String email, TypeOfUser typeOfUser, long expiresIn, LocalDateTime lastTimePenaltyPointsReset) {
		this.accessToken = accessToken;
		this.typeOfUser = typeOfUser;
		this.email = email;
		this.expiresIn = expiresIn;
		this.lastTimePenaltyPointsReset = lastTimePenaltyPointsReset;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public TypeOfUser getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(TypeOfUser typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getLastTimePenaltyPointsReset() {
		return lastTimePenaltyPointsReset;
	}

	public void setLastTimePenaltyPointsReset(LocalDateTime lastTimePenaltyPointsReset) {
		this.lastTimePenaltyPointsReset = lastTimePenaltyPointsReset;
	}
	
}
