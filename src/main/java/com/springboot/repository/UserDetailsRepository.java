package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springboot.model.UserDetails;

/***
 * 
 * @author Jeet Khatri
 * @date 09-Jan-2019
 * @link https://github.com/JeetKhatri/SpringBoot-many-to-one-with-mysql.git
 *
 */

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

	
}

