package com.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.model.UserDetails;

/***
 * 
 * @author Jeet Khatri
 * @date 09-Jan-2019
 * @link https://github.com/JeetKhatri/Springboot-Authentication-Using-Spring-Security.git
 *
 */

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute("message","welcome to the app...");
		return "welcome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("errorMsg", "Your username and password are invalid.");

		if (logout != null)
			model.addAttribute("msg", "You have been logged out successfully.");

		return "login";
	}
	
	@GetMapping("/signup")
	public String addForm(Model model) {
		model.addAttribute("userForm",new UserDetails());
		return "signup";
	}
	
	@GetMapping("/forgot-password")
	public String forgotpasswordForm(Model model) {
		return "forgotpassword";
	}
}
