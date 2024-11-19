package com.todoapp.TodoManagementApp.security;

import java.util.function.Function;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
	@Bean
	public InMemoryUserDetailsManager setUserDetails() {
		UserDetails user1 = createNewUser("Ankit Payal", "12345");
		UserDetails user2 = createNewUser("Pinak Dixit", "123456");
		
		
		return new InMemoryUserDetailsManager(user1, user2);
	}
	public UserDetails createNewUser(String username, String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		UserDetails user = User.builder()
				.passwordEncoder(passwordEncoder )
				.username(username)
				.password(password)
				.roles("Admin")
				.build();
		return user;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());     //reconfiguring all the urls or authorizing them again
		http.formLogin(withDefaults());
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		return http.build();
	}
}
