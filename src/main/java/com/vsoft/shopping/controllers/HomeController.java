package com.vsoft.shopping.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.shopping.model.User;
import com.vsoft.shopping.service.UserService;
import com.vsoft.shopping.utils.JWTUtil;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	UserService userService ;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTUtil jwtUtil;
	
	
	@PostMapping("/signUp")
	public User signUp(@RequestBody User user) {
		// Validation
		
		
		// Call Service class to signup the user
		user = userService.signUp(user);
		
		// Response to the caller
		return user ;
	}
	
	@PostMapping("/auth")
	public ResponseEntity<String> login(@RequestBody HashMap<String, String> credentials) {
		try {
			String userName = credentials.get("email");
			String pwd = credentials.get("pwd");
			Authentication authentication = new UsernamePasswordAuthenticationToken(userName, pwd);
			authenticationManager.authenticate(authentication);
			String token = jwtUtil.generateToken(userName);
			return new ResponseEntity<String>(token,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("false",HttpStatus.UNAUTHORIZED);
		}
		
	}
}
