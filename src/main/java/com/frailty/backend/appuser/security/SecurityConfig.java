package com.frailty.backend.appuser.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.frailty.backend.appuser.AppUserService;

import lombok.AllArgsConstructor;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

//    private final AppUserService appUserService;
//    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    // this bean overrides the default Spring security chain
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // allow request to be sent
                .authorizeRequests()
//                .antMatchers("/api/v*/registration/**", "/api/v*/login", "/swagger-ui/index.html", "/api/v*/social/answers")
//                .permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
//        http.sessionManagement().sessionCreationPolicy(STATELESS);
//        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        return http.build();
    }
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(appUserService);
//        return provider;
//    }
}
