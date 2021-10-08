package com.vsoft.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vsoft.shopping.model.ShoppingUserDetail;
import com.vsoft.shopping.model.User;
import com.vsoft.shopping.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User signUp(User user) {
		String encodedPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPwd);
		user = this.userRepository.save(user);
		return user;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = this.userRepository.loadUserByUsername(username);
		return new ShoppingUserDetail(u);
	}

}
