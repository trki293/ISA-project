package com.isa.booking_entities.dtos;

public class SystemAdminUpdatePasswordDTO {
	private String newPassword;
	private String confirmNewPassword;
	
	public SystemAdminUpdatePasswordDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public SystemAdminUpdatePasswordDTO(String newPassword, String confirmNewPassword) {
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
}
