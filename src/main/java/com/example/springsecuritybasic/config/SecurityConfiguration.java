package com.example.springsecuritybasic.config;

import com.example.springsecuritybasic.controller.restController.SpringSecurityAPIController;
import com.example.springsecuritybasic.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // define a csrf token
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");


        httpSecurity.securityContext(securityContext -> securityContext.requireExplicitSave(false))
                // always create the jsessionid after the initial login is completed
                // authenticate once and get a session as long as the session token is valid
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                // cors configuration below
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        // allow origin in the port 4200
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        // allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
                        config.setAllowedMethods(Collections.singletonList("*"));
                        // allow the said origin to send credentials to authenticate, this is set to false by default for security reasons
                        config.setAllowCredentials(true);
                        // this allows the server to accept any headers in the request by origins referenced above
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                // csrf configuration below
                // disable csrf for the request that match the patterns below (ignoringRequestMatchers)
                .csrf(csrfCustomizer -> csrfCustomizer.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/information/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                // execute the csrfCookieFilter after the basic authentication filter
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                // Filter responsible to generate the jwt token
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                // Filter responsible to validate the jwt token
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                // authorize different requests depending on the endpoint
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        // the request that match the pattern below will be authenticated and need to have the specified authority
                        .requestMatchers("/account/**").hasAuthority("VIEWCARDS")
                        // the request that match the pattern below will be authenticated but do not need to have any authority
                        .requestMatchers("/user").authenticated()
                        // the requests that match the regex below are not protected by authentication and are open to all users
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
