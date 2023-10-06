package com.teamsix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamsix.dto.User;
import com.teamsix.entity.UserInfo;
import com.teamsix.openFeign.UserFeign;

@Service
public class AuthService {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserFeign userFeign;
	
	@Autowired
	private JwtService jwtService;
	
//	public String saveUser(UserCredential user) {
//		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
//		this.repository.save(user);
//		return "User saved to db";
//	}
	
	public String saveUser(User user) {
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		return this.userFeign.register(user).getBody();
	}
	
	 public String generateToken(String username) {
	        return jwtService.generateToken(username);
	    }

	    public void validateToken(String token) {
	        jwtService.validateToken(token);
	    }
	
}
