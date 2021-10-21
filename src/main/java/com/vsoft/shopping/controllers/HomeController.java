package com.vsoft.shopping.controllers;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.shopping.model.User;
import com.vsoft.shopping.model.UserResponse;
import com.vsoft.shopping.service.UserService;
import com.vsoft.shopping.utils.JWTUtil;

@RestController
@RequestMapping("/home")
@CrossOrigin()
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
	public ResponseEntity<UserResponse> login(@RequestBody HashMap<String, String> credentials) {
		try {
			String userName = credentials.get("email");
			String pwd = credentials.get("pwd");
			Authentication authentication = new UsernamePasswordAuthenticationToken(userName, pwd);
			authentication = authenticationManager.authenticate(authentication);
			String role  = ((List<GrantedAuthority>) authentication.getAuthorities()).get(0).getAuthority();
			String token = jwtUtil.generateToken(userName);
			UserResponse res = new UserResponse(token, role);
			return new ResponseEntity<UserResponse>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserResponse>(HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@GetMapping(value = "/logout")
	public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
	}

}
