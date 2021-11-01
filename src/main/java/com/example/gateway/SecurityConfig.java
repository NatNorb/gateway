package com.example.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.ArrayList;
import java.util.List;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                .authorizeExchange()
                .pathMatchers("/create/salesrep").hasAuthority("ROLE_ADMIN")
                .pathMatchers("/**").permitAll()
//                .pathMatchers("/convert/**").permitAll()

//                .pathMatchers("/**").hasAuthority("ROLE_ADMIN")
//                .pathMatchers("/**").hasAuthority("ROLE_SALESREP")
//                .pathMatchers(HttpMethod.GET,"/opps/**").permitAll()
//                .pathMatchers(HttpMethod.POST,"/salesrep").permitAll()
//                .pathMatchers("/salesrep").hasAuthority("ROLE_ADMIN")
                .anyExchange().authenticated()
                .and().httpBasic()
                .and().build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("salesrep")
                .password(passwordEncoder.encode("salesrep"))
                .roles("SALESREP")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        List<UserDetails> users = new ArrayList<>();
        users.add(user);
        users.add(admin);

        return new MapReactiveUserDetailsService(users);
    }
}