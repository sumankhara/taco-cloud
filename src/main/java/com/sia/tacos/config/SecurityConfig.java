package com.sia.tacos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// In-memory user store
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.inMemoryAuthentication()//.passwordEncoder(NoOpPasswordEncoder.getInstance())
//				.withUser("buzz")
//					.password("{noop}infinity")
//					.authorities("ROLE_USER")
//				.and()
//				.withUser("woody")
//					.password("{noop}bullseye")
//					.authorities("ROLE_USER");
//	}
	
	// LDAP-backed user store
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
				.userSearchBase("ou=people")
				.userSearchFilter("(uid={0})")
				.groupSearchBase("ou=groups")
				.groupSearchFilter("member={0}")
				.contextSource()
				.url("ldap://localhost:8389/dc=tacocloud,dc=org");
	}
}
