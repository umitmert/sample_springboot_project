package com.iyzico.config;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.ibss.util.Util;
import com.iyzico.domain.Role;
import com.iyzico.domain.User;
import com.iyzico.service.RoleService;
import com.iyzico.service.UserService;




@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider, Serializable {
	private static final long serialVersionUID = 1L;

    @Autowired(required=true)
    private UserService userService;
    
    @Autowired(required=true)
    private RoleService roleService;
	
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		System.out.println("username : " + authentication.getName() + " password " +  authentication.getCredentials().toString());
		User userDTO = new User(); 
		userDTO.setUsername(authentication.getName());
		userDTO.setPassword( authentication.getCredentials().toString());

		User user = null;
 
	    try {
			user = userService.findByUsername(userDTO.getUsername());
			System.out.println(user.getPassword());
		} catch (Exception e) {
			String message = "Incorrect username.error";	
			throw new BadCredentialsException("Unable to sign in. " + message);
		}
		
		if (user != null  &&  user.getPassword().equals(Util.encryptPassword(userDTO.getUsername(), userDTO.getPassword()) )) {
			
			Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			Role role = userService.userRoleId(user.getId());
			authorities.add(role);
 
	        return new UsernamePasswordAuthenticationToken(user, authentication, authorities);
		} else {
			String message = "Incorrect credentials.";
			throw new BadCredentialsException("Unable to sign in. " + message);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}