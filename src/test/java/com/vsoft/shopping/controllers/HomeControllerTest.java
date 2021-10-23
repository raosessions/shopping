package com.vsoft.shopping.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.vsoft.shopping.model.UserResponse;
import com.vsoft.shopping.service.UserService;
import com.vsoft.shopping.utils.JWTUtil;

public class HomeControllerTest {

	@InjectMocks
	HomeController homeController;

	@Mock
	UserService userService;
	@Mock
	AuthenticationManager authenticationManager;
	@Mock
	JWTUtil jwtUtil;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void endUserLogin() {
		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials.put("email", "user4@gmail.com");
		credentials.put("pwd", "password");
		
		Mockito.when(jwtUtil.generateToken("user4@gmail.com")).thenReturn("dummytoken");
		
		Collection<GrantedAuthority> authrity = new ArrayList<GrantedAuthority>();
		authrity.add(new SimpleGrantedAuthority("USER"));
		Authentication value =  new UsernamePasswordAuthenticationToken("user4@gmail.com",null,authrity);
		Mockito.when(authenticationManager.authenticate(Mockito.anyObject())).thenReturn(value);
		
		ResponseEntity<UserResponse> entity = homeController.login(credentials);
		Assertions.assertNotNull(entity,"Null entity recevied..");
		Assertions.assertEquals("dummytoken", entity.getBody().getToken());
	}

	//@Test
	public void invalidUserLogin() {
		HashMap<String, String> credentials = new HashMap<String, String>();
		credentials.put("email", "user4@gmail.com");
		credentials.put("pwd", "wroingpassword");
		
		// Mockito.when(jwtUtil.generateToken("user4@gmail.com")).thenReturn("dummytoken");
		RuntimeException ex = new RuntimeException();
		Mockito.when(authenticationManager.authenticate(Mockito.anyObject())).thenThrow(ex);
		
		ResponseEntity<UserResponse> entity = homeController.login(credentials);
		Assertions.assertNull(entity.getBody(),"Null entity recevied..");
		Assertions.assertEquals(HttpStatus.UNAUTHORIZED, entity.getStatusCode());
	}
}
