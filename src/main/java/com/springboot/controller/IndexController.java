package com.springboot.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
