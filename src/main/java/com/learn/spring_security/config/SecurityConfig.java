package com.learn.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //Security Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity sec){
        //Disable CSRF Auth
        sec.csrf(customizer->customizer.disable());
        //Enable Authorization for each request
        sec.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        //Enable form login
        sec.formLogin(Customizer.withDefaults());
        //Enable website data view for postman
        sec.httpBasic(Customizer.withDefaults());
        //Make website Stateless
        sec.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return sec.build();
    }

    //Adding users who can access - hardcoding
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1=
                User
                        .withDefaultPasswordEncoder()
                        .username("Rishi")
                        .password("R123")
                        .roles("USER")
                        .build();
        UserDetails user2=
                User
                        .withDefaultPasswordEncoder()
                        .username("Harsh")
                        .password("H123")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

}
