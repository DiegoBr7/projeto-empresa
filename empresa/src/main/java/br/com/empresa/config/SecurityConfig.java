package br.com.empresa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

@Autowired private CustomUserDetailsService userDetailsService;
@Autowired private PasswordEncoder passwordEncoder;
@Autowired private JwtAuthenticationFilter jwtFilter;

@Bean
    public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider p = new DaoAuthenticationProvider();
    p.setUserDetailsService(userDetailsService);
    p.setPasswordEncoder(passwordEncoder);
    return p;
}

@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws  Exception {
    return cfg.getAuthenticationManager();
}

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth ->  auth
                    .requestMatchers("/auth/login" , "/usuario/enviar").permitAll()
                    .requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/api/produtos/**").authenticated()
                    .requestMatchers("/produto/**").authenticated()
                    .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    http.headers(h -> h.frameOptions(fo -> fo.disable()));

    return http.build();
}


}
