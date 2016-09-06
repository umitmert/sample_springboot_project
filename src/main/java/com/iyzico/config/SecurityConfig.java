package com.iyzico.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackageClasses={com.iyzico.service.UserServiceImpl.class, com.iyzico.config.CustomAuthenticationProvider.class} )
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired(required=true)
	private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userService;

	@Autowired
	public void configureGlobal(UserDetailsService userDetailsService, AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider).eraseCredentials(false);
	} 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);

		http
		.authorizeRequests()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/css/**").permitAll()
			.antMatchers("/admin/**").authenticated()
			.antMatchers("/user/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
		.formLogin().usernameParameter("username").passwordParameter("password")
			.loginPage("/login")
			.failureUrl("/login/error")
			.defaultSuccessUrl("/")
			.successForwardUrl("/") 
			.permitAll()
			.and()
		.logout()
			.invalidateHttpSession(true)
			.permitAll()
			.logoutSuccessUrl("/");
		//        .exceptionHandling()
		//            .accessDeniedHandler(accessDeniedExceptionHandler);

	}
	
}