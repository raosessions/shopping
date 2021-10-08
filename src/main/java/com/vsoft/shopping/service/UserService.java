package com.vsoft.shopping.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vsoft.shopping.model.User;

public interface UserService extends UserDetailsService {
	User signUp(User dto);

	List<User> getUsers();
	
}
