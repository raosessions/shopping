package com.vsoft.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.shopping.model.User;
import com.vsoft.shopping.service.UserService;

@RestController
public class HomeController {
	
	@Autowired
	UserService userService ;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/signUp")
	public User signUp(@RequestBody User user) {
		// Validation
		
		
		// Call Service class to signup the user
		user = userService.signUp(user);
		
		// Response to the caller
		return user ;
	}
	
	@PostMapping("/auth")
	public ResponseEntity<String> login(@RequestBody User user) {
		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
			authenticationManager.authenticate(authentication);
			return new ResponseEntity<String>("true",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false",HttpStatus.UNAUTHORIZED);
		}
		
	}
}
