package com.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@Controller
@RequestMapping("/users")
public class UserDetailsController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public String listOfUserDetails(Model model) {
		model.addAttribute("userDetails",userDetailsService.getListOfUserDetails());
		return "userList";
	}
	
	@GetMapping("/insert")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public String addForm(Model model) {
		model.addAttribute("userForm",new UserDetails());
		return "userInsert";
	}
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String addUpdateUserDetails(@Valid @ModelAttribute UserDetails userDetails, Model model) {
		String name = userDetailsService.addUserDetails(userDetails);
		if(name.isEmpty()) 
			model.addAttribute("message","Fail to add User");
		else {
			String message="";
			if(userDetails.getId()==0) {
				message="added";
			}else {
				message="updated";
			}
			model.addAttribute("message","User "+name+" "+message+" successfully.");
		}
		return "welcome";
	}
	
	@GetMapping("edit/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public String editForm(@PathVariable int id, Model model) {
		model.addAttribute("userForm",userDetailsService.getUserDetails(id));
		return "userUpdate";
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public UserDetails getUserDetails(@PathVariable(name="id") int id) {
		return userDetailsService.getUserDetails(id);
	}
	
	@GetMapping("delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteUserDetails(@PathVariable int id, Model model) {
		if(userDetailsService.deleteUserDetails(id).getStatusCode().isError()) {
			model.addAttribute("message","Fail to delete User");
		}else {
			model.addAttribute("message","User deleted successfully.");
		}
		return "welcome";
	}
	
}
