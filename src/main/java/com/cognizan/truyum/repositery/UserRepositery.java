package com.cognizan.truyum.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizan.truyum.model.User;

@Repository
public interface UserRepositery extends JpaRepository<User, Integer> {
	
	@Query(value = "select * from user where us_name=?1", nativeQuery = true)
    User findbyUserName(String username);

	

}
