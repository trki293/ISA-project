package com.isa.booking_entities.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.dtos.DeleteAccountRequestNewDTO;
import com.isa.booking_entities.models.requests.DeleteAccountRequest;
import com.isa.booking_entities.models.requests.StateOfDeleteAccountRequest;
import com.isa.booking_entities.models.users.Users;
import com.isa.booking_entities.repositories.IDeleteAccountRequestRepository;
import com.isa.booking_entities.services.interfaces.IDeleteAccountRequestService;

@Service
public class DeleteAccountRequestService implements IDeleteAccountRequestService {
	
	private IDeleteAccountRequestRepository iDeleteAccountRequestRepository;
	
	@Autowired
	public DeleteAccountRequestService(IDeleteAccountRequestRepository iDeleteAccountRequestRepository) {
		this.iDeleteAccountRequestRepository = iDeleteAccountRequestRepository;
	}
	
	@Override
	public DeleteAccountRequest save(DeleteAccountRequest deleteAccountRequest) {
		// TODO Auto-generated method stub
		return iDeleteAccountRequestRepository.save(deleteAccountRequest);
	}

	@Override
	public DeleteAccountRequest getById(long id) {
		// TODO Auto-generated method stub
		return iDeleteAccountRequestRepository.findById(id).orElse(null);
	}

	@Override
	public List<DeleteAccountRequest> getAllRequestsThatHaveNoResponse() {
		return iDeleteAccountRequestRepository.findAll().stream().filter(deleteAccountRequestIt -> deleteAccountRequestIt.getStateOfRequest()==StateOfDeleteAccountRequest.CREATED).collect(Collectors.toList());
	}

	@Override
	public Boolean checkIfExistCurrentRequest(Users user) {
		DeleteAccountRequest deleteAccountRequest = iDeleteAccountRequestRepository.findAll().stream().filter(deleteAccountRequestIt -> user.getEmail().equals(deleteAccountRequestIt.getUserWhoDeleteAccount().getEmail()) && deleteAccountRequestIt.getStateOfRequest()==StateOfDeleteAccountRequest.CREATED).findFirst().orElse(null);
		return deleteAccountRequest!=null;
	}

	@Override
	public DeleteAccountRequest createDeleteAccountRequest(DeleteAccountRequestNewDTO deleteAccountRequestNewDTO,
			Users user) {
		DeleteAccountRequest deleteAccountRequest = new DeleteAccountRequest();
		deleteAccountRequest.setStateOfRequest(StateOfDeleteAccountRequest.CREATED);
		deleteAccountRequest.setText(deleteAccountRequestNewDTO.getText());
		deleteAccountRequest.setUserWhoDeleteAccount(user);
		return deleteAccountRequest;
		
	}

}
