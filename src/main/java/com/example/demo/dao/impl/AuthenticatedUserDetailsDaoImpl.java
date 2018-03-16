package com.example.demo.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.AuthenticatedUserDetailsDao;
import com.example.demo.model.Role;
import com.example.demo.model.User;

/**
 * @see AuthenticatedUserDetailsDao
 *
 */
@Repository
public class AuthenticatedUserDetailsDaoImpl implements AuthenticatedUserDetailsDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public AuthenticatedUserDetailsDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @see AuthenticatedUserDetailsDao
	 */
	public User loadUserDetails(String userName) {
		String userDetailsSQL = "SELECT * FROM springboot.user, role INNER JOIN user_role WHERE user_role.user_id = user.user_id and user_role.role_id=role.role_id and user_name = ?";
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = this.jdbcTemplate.query(userDetailsSQL, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userName);
			}

		}, new ResultSetExtractor<User>() {

			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				User user = new User();
				Set<Role> roles = new HashSet<Role>();
				if (rs.next()) {
					user.setUserId(rs.getInt("user_id"));
					user.setUserName(rs.getString("user_name"));

					String password = rs.getString("password");
					if (password != null) {
						password = encoder.encode(password);
					}
					user.setPassword(password);
					user.setActiveStatus(rs.getString("isAccountActive"));
					Role role = new Role();
					role.setRoleId(rs.getInt("role_id"));
					role.setRoleName(rs.getString("role"));
					roles.add(role);
				}
				user.setRolesSet(roles);
				// TODO Auto-generated method stub
				return user;
			}
		});
		return user;
	}
}
