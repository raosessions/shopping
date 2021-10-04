package com.vsoft.shopping.repository;

import java.util.List;

import com.vsoft.shopping.model.User;

public interface UserRepository {
	User save(User user);

	List<User> getUsers();
}
