package com.springboot.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.springboot.model.UserDetails;
import com.springboot.repository.UserDetailsRepository;

@Service
public class CustomAuthenticationProviderService implements AuthenticationProvider  {
	
	@Autowired
	private UserDetailsRepository userRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authenticationToken = null;
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetails user = userRepository.findByName(username);
		if(user != null) {
			if(username.equals(user.getName()) && BCrypt.checkpw(password, user.getPassword())) {
				Collection<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
				authenticationToken = new UsernamePasswordAuthenticationToken(
						new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities), password, grantedAuthorities);
			}
		} else {
			throw new UsernameNotFoundException("User name "+username+" not found");
		}
		return authenticationToken;
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
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
