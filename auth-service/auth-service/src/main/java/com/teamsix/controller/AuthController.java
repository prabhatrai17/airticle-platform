package com.teamsix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamsix.dto.AuthRequest;
import com.teamsix.dto.User;
import com.teamsix.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	 @Autowired
	    private AuthService service;

	    @Autowired
	    private AuthenticationManager authenticationManager;

//	    @PostMapping("/register")
//	    public String addNewUser(@RequestBody UserCredential user) {
//	        return service.saveUser(user);
//	    }
	    
	    @PostMapping("/register")
	    public String addNewUser(@RequestBody User user) {
	    	return this.service.saveUser(user);
	    }

	    @PostMapping("/token")
	    public String getToken(@RequestBody AuthRequest authRequest) {
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        if (authenticate.isAuthenticated()) {
	            return service.generateToken(authRequest.getUsername());
	        } else {
	            throw new RuntimeException("invalid access");
	        }
	    }

	    @GetMapping("/validate")
	    public String validateToken(@RequestParam("token") String token) {
	        service.validateToken(token);
	        return "Token is valid";
	    }

}
