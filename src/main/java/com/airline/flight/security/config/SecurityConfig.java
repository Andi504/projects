package com.airline.flight.security.config;

import com.airline.flight.security.filter.CustomAuthenticationFilter;
import com.airline.flight.security.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(false);
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    private static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/v1/user/login");
        http.csrf().disable().headers().frameOptions().deny()
                .and()
                .authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll();
        http.headers().frameOptions().disable();
        http.authorizeRequests().antMatchers(GET, "/api/v1/user/token/refresh").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/v1/user/login").permitAll();
        http.authorizeRequests().antMatchers(POST, "/api/v1/role/save").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/v1/user/save/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST, "/api/v1/user/role/**").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/user/{id}").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/api/v1/flight/save").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/api/v1/flight").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/api/v1/trip/save").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/trip/update").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/api/v1/trip/trips").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/trip/{id}/send-approval").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(POST, "/api/v1/trip/flights").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(DELETE, "/api/v1/trip/{id}").hasAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers(PUT, "/api/v1/trip/{id}").hasAuthority("ROLE_USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
