package com.vsoft.shopping.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vsoft.shopping.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public User save(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("insert into users(name,email,password) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPassword());
				return ps;
			}
		}, keyHolder );
		user.setId((int) keyHolder.getKeys().get("userid"));
		System.out.println(keyHolder.getKeyList());
		user.setPassword("********");
		return user;
	}

	@Override
	public List<User> getUsers() {
		return null;
	}

}
