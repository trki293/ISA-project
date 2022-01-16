package com.isa.booking_entities.services.interfaces;

import com.isa.booking_entities.models.requests.DeleteAccountRequest;
import com.isa.booking_entities.models.requests.DeleteAccountRequestResponse;
import com.isa.booking_entities.models.users.SystemAdmin;

public interface IDeleteAccountRequestResponseService {
	DeleteAccountRequestResponse save(DeleteAccountRequestResponse deleteAccountRequestResponse);
	DeleteAccountRequestResponse createDeleteAccountRequestResponse(SystemAdmin systemAdmin, DeleteAccountRequest deleteAccountRequest, String text);
}
