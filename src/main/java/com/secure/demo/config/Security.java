package com.secure.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Security{
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        List<UserDetails> userDetailsList = new ArrayList<>();

        UserDetails user1 = User.builder()
                .username("chris")
                .password(passwordEncoder().encode("1234"))
                .roles("user")
                .build();
        userDetailsList.add(user1);

        UserDetails user2 = User.builder()
                .username("alice")
                .password(passwordEncoder().encode("5678"))
                .roles("admin")
                .build();
        userDetailsList.add(user2);

        return new InMemoryUserDetailsManager(userDetailsList);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(6);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((authz)-> {
                    try {
                        authz
                                //from the most restrictive stuff to the least
                                .requestMatchers("/admin").hasRole("admin")
                                .requestMatchers("/user").hasAnyRole("user","admin")
                                .requestMatchers("/").permitAll()
                                .and()
                                .formLogin();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .httpBasic(Customizer.withDefaults());
return http.build();


    }
}
