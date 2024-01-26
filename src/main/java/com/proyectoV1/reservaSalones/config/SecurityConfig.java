package com.proyectoV1.reservaSalones.config;

import com.proyectoV1.reservaSalones.security.JwtAuthenticationFilter;
import com.proyectoV1.reservaSalones.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtil);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoderForFront() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public static class ApiSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;

        @Autowired
        public ApiSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
            this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        }
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable()
                    .authorizeRequests()
                    .requestMatchers("/api/login").permitAll()  // Permitir acceso a /api/login sin autenticación
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        }
    }
}