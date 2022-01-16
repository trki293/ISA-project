package com.isa.booking_entities.dtos;

public class DeleteAccountRequestResponseNewDTO {
	private String text;
	private String systemAdminEmail;
	private long deleteAccountRequestId;
	private boolean approved;
	
	public DeleteAccountRequestResponseNewDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public DeleteAccountRequestResponseNewDTO(String text, String systemAdminEmail, long deleteAccountRequestId,
			boolean approved) {
		this.text = text;
		this.systemAdminEmail = systemAdminEmail;
		this.deleteAccountRequestId = deleteAccountRequestId;
		this.approved = approved;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSystemAdminEmail() {
		return systemAdminEmail;
	}

	public void setSystemAdminEmail(String systemAdminEmail) {
		this.systemAdminEmail = systemAdminEmail;
	}

	public long getDeleteAccountRequestId() {
		return deleteAccountRequestId;
	}

	public void setDeleteAccountRequestId(long deleteAccountRequestId) {
		this.deleteAccountRequestId = deleteAccountRequestId;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
}
