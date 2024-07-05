package com.example.springsecuritybasic.config;

import com.example.springsecuritybasic.filter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    //there is a lot of configurations that I removed but can be seen in previous commits on GitHub
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // with the line below we are telling the spring security to not generate jsession, we will generate ourselves
        httpSecurity.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                // Filter responsible to generate the jwt token
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                // Filter responsible to validate the jwt token
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/account/**","/user").authenticated()
                        .requestMatchers("/information/**", "/register","/favicon.ico","/error").permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    /*@Bean
    public InMemoryUserDetailsManager userDetailsService(){

        // the method is deprecated but there is no intents of removing it
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .build();

        return new InMemoryUserDetailsManager(admin, user);

    }*/

    /*@Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        System.out.println("userDetailsService");
        return new JdbcUserDetailsManager(dataSource);
    }*/

    // only noop is supported for encoding and decoding
    // NoOpPasswordEncoder is not recommended for production usage. Use only for non-prod.
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    // Only bcrypt is supported for encoding and decoding
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    // Custom one created by myself, this method does not define a specific encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        // when encoded passwords will append a prefix equal to the type of encoding, for example , the bcrypt method will append the prefix {bcrypt}
        // in encode because it is not specified it will always append the prefix {bcrypt}
        // for better understanding of this part see passwords on db
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
