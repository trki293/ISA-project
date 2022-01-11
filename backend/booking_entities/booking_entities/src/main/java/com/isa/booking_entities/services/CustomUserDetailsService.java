package com.isa.booking_entities.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.ServerSecurityMarker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isa.booking_entities.models.users.Users;
import com.isa.booking_entities.repositories.IUsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private IUsersRepository iUsersRepository;
	
	@Autowired
	public CustomUserDetailsService(IUsersRepository iUsersRepository) {
		this.iUsersRepository = iUsersRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = iUsersRepository.findAll().stream().filter(userIt -> userIt.getEmail().equals(username)).findFirst().orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", username));
		} else {
			return user;
		}
	}
}
