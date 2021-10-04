package com.vsoft.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.shopping.model.User;
import com.vsoft.shopping.service.UserService;

@RestController
public class HomeController {
	
	@Autowired
	UserService userService ;
	
	@PostMapping("/signUp")
	public User signUp(@RequestBody User user) {
		// Validation
		
		
		// Call Service class to signup the user
		user = userService.signUp(user);
		
		// Response to the caller
		return user ;
	}
}
