package com.springboot.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.model.UserDetails;
import com.springboot.repository.UserDetailsRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetails user = userDetailsRepository.findByName(username);
		if(user == null) {
			throw new UsernameNotFoundException("User name "+username+" not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), "{noop}"+user.getPassword(),getGrantedAuthorities(user));
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(UserDetails user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if(user.getRoles().getName().equals("admin")) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return grantedAuthorities;
	}
}
