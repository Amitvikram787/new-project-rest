package com.cognizan.truyum.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizan.truyum.model.Role;
@Repository
public interface RoleRepositery extends JpaRepository<Role, Integer> {
	
	Role findByRoleId(int id);

}
