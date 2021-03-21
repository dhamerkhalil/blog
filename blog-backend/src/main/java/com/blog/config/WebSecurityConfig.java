package com.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.blog.business.service.CustomeUserDetailsService;
import com.blog.security.CustomAuthenticationFailureHandler;
import com.blog.security.CustomSuccessHandler;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomeUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable();
		 http.authorizeRequests()
         .antMatchers(HttpMethod.POST,"/add-user").permitAll()
         .antMatchers("/", "/blog/**").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .loginPage("/login").permitAll().loginProcessingUrl("/doLogin")
         .usernameParameter("user").passwordParameter("pass")
         .successHandler(successHandler()).failureUrl("/login")
         .failureHandler(failHandler())
         .permitAll()
         .and()
         .logout()
         .invalidateHttpSession(true)
         .clearAuthentication(true)
         .permitAll();
	}
	
	@Bean
	public CustomSuccessHandler successHandler() {
	    return new CustomSuccessHandler();
	}
	
	@Bean
	public CustomAuthenticationFailureHandler failHandler() {
	    return new CustomAuthenticationFailureHandler();
	}

}
