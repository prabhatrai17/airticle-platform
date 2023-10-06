package com.teamsix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.teamsix.dto.User;
import com.teamsix.openFeign.UserFeign;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UserFeign userFeign;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userFeign.getUserByUsername(username).getBody();
		return new CustomUserDetails(user);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserCredential> credential= this.repository.findByName(username);
//		return credential.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
//	}

}
