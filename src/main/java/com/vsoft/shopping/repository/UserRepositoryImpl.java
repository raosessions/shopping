package com.vsoft.shopping.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
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

	@Override
	public User loadUserByUsername(String userName) {
		List<User> users = jdbcTemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement("select * from users where email = ? ");
				ps.setString(1, userName);
				return ps;
			}
		}, new UserRowMapper());
		return users.get(0);
	}

}
class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User u = new User();
		u.setEmail(rs.getString("email"));
		u.setPassword(rs.getString("password"));
		u.setName(rs.getString("name"));
		return u;
	}
	
}
