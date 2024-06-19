package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
	            .requestMatchers("/user/login", "/static/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        .formLogin(formLogin -> formLogin
	            .loginPage("/user/login")
	            .loginProcessingUrl("/user/loginProcess")
	            .defaultSuccessUrl("/daelim/list")
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
	            .logoutSuccessUrl("/")
	            .invalidateHttpSession(true)
	        );
	    return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
