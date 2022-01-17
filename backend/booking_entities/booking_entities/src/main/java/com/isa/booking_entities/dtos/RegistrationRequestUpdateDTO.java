package com.isa.booking_entities.dtos;

import com.isa.booking_entities.models.requests.StateOfRequest;

public class RegistrationRequestUpdateDTO {
	private long requestId;
	private StateOfRequest stateOfRequest;
	private String reasoneForRejectionOfRequest;
	
	public RegistrationRequestUpdateDTO() {
		// TODO Auto-generated constructor stub
	}

	public RegistrationRequestUpdateDTO(long requestId, StateOfRequest stateOfRequest, String reasoneForRejectionOfRequest) {
		this.requestId = requestId;
		this.stateOfRequest = stateOfRequest;
		this.reasoneForRejectionOfRequest = reasoneForRejectionOfRequest;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public StateOfRequest getStateOfRequest() {
		return stateOfRequest;
	}

	public void setStateOfRequest(StateOfRequest stateOfRequest) {
		this.stateOfRequest = stateOfRequest;
	}

	public String getReasoneForRejectionOfRequest() {
		return reasoneForRejectionOfRequest;
	}

	public void setReasoneForRejectionOfRequest(String reasoneForRejectionOfRequest) {
		this.reasoneForRejectionOfRequest = reasoneForRejectionOfRequest;
	}
	
}
