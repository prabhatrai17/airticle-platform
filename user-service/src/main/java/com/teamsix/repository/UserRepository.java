package com.teamsix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamsix.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByUserId(Long userId);
	public List<User> findByRole(String role);

	public User findByUsername(String usernames);

}
