package com.isa.booking_entities.dtos;

public class InstructionsComplaintResponseNewDTO {
	private long instructionsComplaintId;
	private String systemAdminEmail;
	private String text;
	
	public InstructionsComplaintResponseNewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public InstructionsComplaintResponseNewDTO(long instructionsComplaintId, String systemAdminEmail, String text) {
		this.instructionsComplaintId = instructionsComplaintId;
		this.systemAdminEmail = systemAdminEmail;
		this.text = text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public void setInstructionsComplaintId(long instructionsComplaintId) {
		this.instructionsComplaintId = instructionsComplaintId;
	}
	
	public void setSystemAdminEmail(String systemAdminEmail) {
		this.systemAdminEmail = systemAdminEmail;
	}
	
	public String getText() {
		return text;
	}
	
	public String getSystemAdminEmail() {
		return systemAdminEmail;
	}
	
	public long getInstructionsComplaintId() {
		return instructionsComplaintId;
	}
}
