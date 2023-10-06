package com.teamsix.openFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.teamsix.dto.User;


@FeignClient(name = "USER-SERVICE")
public interface UserFeign {
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserByUserId(@PathVariable Long userId);


}
