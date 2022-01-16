package com.isa.booking_entities.services;

import com.isa.booking_entities.models.requests.DeleteAccountRequest;
import com.isa.booking_entities.models.requests.DeleteAccountRequestResponse;
import com.isa.booking_entities.models.users.SystemAdmin;
import com.isa.booking_entities.repositories.IDeleteAccountRequestResponseRepository;
import com.isa.booking_entities.services.interfaces.IDeleteAccountRequestResponseService;

public class DeleteAccountRequestResponseService implements IDeleteAccountRequestResponseService {
	
	private IDeleteAccountRequestResponseRepository iDeleteAccountRequestResponseRepository;
	
	@Override
	public DeleteAccountRequestResponse save(DeleteAccountRequestResponse deleteAccountRequestResponse) {
		return iDeleteAccountRequestResponseRepository.save(deleteAccountRequestResponse);
	}

	@Override
	public DeleteAccountRequestResponse createDeleteAccountRequestResponse(SystemAdmin systemAdmin,
			DeleteAccountRequest deleteAccountRequest, String text) {
		DeleteAccountRequestResponse deleteAccountRequestResponse = new DeleteAccountRequestResponse();
		deleteAccountRequestResponse.setDeleteAccountRequest(deleteAccountRequest);
		deleteAccountRequestResponse.setSystemAdminWhoReviewRequest(systemAdmin);
		deleteAccountRequestResponse.setText(text);
		return deleteAccountRequestResponse;
	}

}
