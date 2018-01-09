package com.josalero.webreactive.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfiguration {

	   @Bean
	    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
	        return http.csrf().disable()
	        		.httpBasic().and()
	            .authorizeExchange()
	            .pathMatchers(HttpMethod.GET, "/articles/**").permitAll()
	            .pathMatchers(HttpMethod.POST, "/articles/**").authenticated()
	            //.pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)
	            .anyExchange().permitAll()
	            .and()
	            .build();
	    }

		@Bean
		public MapReactiveUserDetailsService reactiveUserDetailsService() {
			UserDetails user = User.withDefaultPasswordEncoder()
								   .username("user")
								   .password("password")
								   .roles("USER")
								   .build();
			UserDetails admin = User.withDefaultPasswordEncoder()
								    .username("admin")
								    .password("password")
								    .roles("USER","ADMIN")
								    .build();
			return new MapReactiveUserDetailsService(user, admin);
		}
}
