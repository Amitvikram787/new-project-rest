package com.cognizan.truyum.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static Logger LOGGER =LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	
@Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception
 {
	//auth.userDetailsService(inMemoryUserDetailsManager());
	
	auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
 }

@Bean
public PasswordEncoder passwordEncoder()
{
	LOGGER.info("Start");
	return new BCryptPasswordEncoder();
}
@Override
protected void configure(HttpSecurity httpSecurity) throws Exception
{
	httpSecurity.cors();
	httpSecurity.csrf().disable().httpBasic()
	.and().authorizeRequests()
	.antMatchers("/authenticate").permitAll()
	.antMatchers("/users")
	.anonymous()
	.antMatchers("/menu-items/**").permitAll()
	.anyRequest().authenticated().and().addFilter(new JwtAuthorizationFilter(authenticationManager()));
}



/*@Bean
public InMemoryUserDetailsManager inMemoryUserDetailsManager()
{
	LOGGER.info("Start");
	List<UserDetails> userDetailsList=new ArrayList<>();
	userDetailsList.add(User.withUsername("user").password(passwordEncoder().
			encode("pwd")).roles("USER").build());
	userDetailsList.add(User.withUsername("admin").password(passwordEncoder().
			encode("pwd")).roles("ADMIN").build());
	LOGGER.info("End");
	
	return   new InMemoryUserDetailsManager(userDetailsList);
	
}
*/





}

