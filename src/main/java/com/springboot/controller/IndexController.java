package com.springboot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 
 * @author Jeet Khatri
 * @date 09-Jan-2019
 * @link https://github.com/JeetKhatri/Springboot-Authentication-Using-Spring-Security.git
 *
 */

@RestController
public class IndexController {

	
	@RequestMapping("/")
	public String welcome() {
		return "welcome to the app";
	}

	
	
}
