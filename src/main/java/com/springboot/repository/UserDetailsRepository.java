package com.springboot.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.model.UserDetails;

/***
 * 
 * @author Jeet Khatri
 * @date 09-Jan-2019
 * @link https://github.com/JeetKhatri/Springboot-Authentication-Using-Spring-Security.git
 *
 */

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

	UserDetails findByName(String userName);
	UserDetails findByResetPasswordToken(String resetPasswordToken);
	List<UserDetails> findByRolesName(String roleName);
}

