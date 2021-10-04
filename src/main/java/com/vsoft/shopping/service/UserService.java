package com.vsoft.shopping.service;

import java.util.List;

import com.vsoft.shopping.model.User;

public interface UserService {
	User signUp(User dto);

	List<User> getUsers();
}
