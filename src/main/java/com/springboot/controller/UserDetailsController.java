package com.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.UserDetails;
import com.springboot.service.UserDetailsService;

/***
 * 
 * @author Jeet Khatri
 * @date 09-Jan-2019
 * @link https://github.com/JeetKhatri/Springboot-Authentication-Using-Spring-Security.git
 *
 */

@RestController
@RequestMapping("/users")
public class UserDetailsController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping
	public List<UserDetails> listOfSubjects() {
		return userDetailsService.getListOfUserDetails();
	}
	
	@PostMapping
	public UserDetails addUserDetails(@Valid @RequestBody UserDetails userDetails) {
		return userDetailsService.addUserDetails(userDetails);
	}
	
	@PutMapping("/{id}")
	public UserDetails updateUserDetails(@Valid @RequestBody UserDetails userDetails, @PathVariable int id) {
		return userDetailsService.updateUserDetails(userDetails, id);
	}
	
	/*
	 * Two way to get variable from url
	 * 1.	@PathVariable int id  (same name required in getMapping)
	 * 2.	@PathVariable(name="id") int subjectId (can use different name)
	 * 
	 */
	@GetMapping("/{id}")
	public UserDetails getUserDetails(@PathVariable(name="id") int id) {
		return userDetailsService.getUserDetails(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUserDetails(@PathVariable int id) {
		return userDetailsService.deleteUserDetails(id);
	}
	
}
