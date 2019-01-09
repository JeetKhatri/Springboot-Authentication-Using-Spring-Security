package com.springboot.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 
 * @author Jeet Khatri
 * @date 16-Oct-2018
 * @link https://github.com/JeetKhatri/SpringBoot-many-to-one-with-mysql.git
 *
 */

@RestController
public class IndexController implements ErrorController {

	
	@RequestMapping("/")
	public String welcome() {
		return "welcome to the app";
	}

	@Override
	@RequestMapping("/error")
	public String getErrorPath() {
		return "No API found";
	}
	
}
