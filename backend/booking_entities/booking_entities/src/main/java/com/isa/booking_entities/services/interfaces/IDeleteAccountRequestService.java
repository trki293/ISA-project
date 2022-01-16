package com.isa.booking_entities.services.interfaces;

import java.util.List;

import com.isa.booking_entities.dtos.DeleteAccountRequestNewDTO;
import com.isa.booking_entities.models.requests.DeleteAccountRequest;
import com.isa.booking_entities.models.users.Users;

public interface IDeleteAccountRequestService {
	DeleteAccountRequest save(DeleteAccountRequest deleteAccountRequest);
	DeleteAccountRequest getById(long id);
	List<DeleteAccountRequest> getAllRequestsThatHaveNoResponse();
	Boolean checkIfExistCurrentRequest(Users user);
	DeleteAccountRequest createDeleteAccountRequest(DeleteAccountRequestNewDTO deleteAccountRequestNewDTO, Users user);
}
