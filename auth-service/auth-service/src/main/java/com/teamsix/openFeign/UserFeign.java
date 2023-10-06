package com.teamsix.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.teamsix.dto.User;


@FeignClient(name = "USER-SERVICE")
public interface UserFeign {
	
	@PostMapping("/user/register")
	public ResponseEntity<String> register(@RequestBody User user);
	
	@GetMapping("/user/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username);

}
