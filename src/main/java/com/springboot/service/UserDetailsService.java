package com.springboot.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.exception.ResourceNotFoundException;
import com.springboot.model.UserDetails;
import com.springboot.repository.UserDetailsRepository;

/***
 * 
 * @author Jeet Khatri
 * @date 09-Jan-2019
 * @link https://github.com/JeetKhatri/Springboot-Authentication-Using-Spring-Security.git
 *
 */

@Service
public class UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	public List<UserDetails> getListOfUserDetails() {
		return userDetailsRepository.findAll();
	}

	public UserDetails addUserDetails(@Valid UserDetails userDetails) {
		return userDetailsRepository.save(userDetails);
	}

	public UserDetails updateUserDetails(@Valid UserDetails userDetails, int id) {
		UserDetails userDetails1 = userDetailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetails", "id", id));

		userDetails1.setName(userDetails.getName());
		userDetails1.setNumber(userDetails.getNumber());
		userDetails1.setPassword(userDetails.getPassword());

        return userDetailsRepository.save(userDetails1);
	}

	public UserDetails getUserDetails(int id) {
		// lambda expression
		return userDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserDetails", "id", id));
	}

	public ResponseEntity<?> deleteUserDetails(int id) {
		// lambda expression
		UserDetails userDetails = userDetailsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserDetails", "id", id));
		userDetailsRepository.deleteById(userDetails.getId());
		return ResponseEntity.ok().build();
	}

}
