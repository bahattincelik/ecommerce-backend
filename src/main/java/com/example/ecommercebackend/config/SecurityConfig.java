package com.example.ecommercebackend.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/products/**", "/users/**", "/orders/**").authenticated()
                        .anyRequest().permitAll())
                .formLogin(form-> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .addFilterBefore(new FirebaseAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        logger.info("FirebaseAuthenticationFilter added to SecurityFilterChain");
        return http.build();
    }

    @Bean
    public HttpFirewall allowAllHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowSemicolon(true);
        firewall.setAllowBackSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        firewall.setAllowUrlEncodedPercent(true);
        firewall.setAllowUrlEncodedPercent(true);
        return firewall;
    }


    public void configure(WebSecurity web) {
        web.httpFirewall(allowAllHttpFirewall());
    }

    // Firebase Authentication Filter
    class FirebaseAuthenticationFilter extends OncePerRequestFilter {
        private static final Logger logger = LoggerFactory.getLogger(FirebaseAuthenticationFilter.class);

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = request.getHeader("Authorization");

            logger.info("Incoming request: " + request.getRequestURI());

            if (token != null && token.startsWith("Bearer ")) {
                logger.info("Authorization header found: " + token);
                token = token.substring(7);
                try {
                    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                    logger.info("Token verified successfully for user: " + decodedToken.getUid());
                    filterChain.doFilter(request, response);
                    return;
                } catch (Exception e) {
                    logger.error("Token verification failed", e);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Firebase Auth Error: " + e.getMessage());
                }
            } else {
                logger.warn("No Authorization header found or incorrect format");
            }
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}







        /*


        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String token = request.getHeader("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                try {
                    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

                    filterChain.doFilter(request, response);
                    return;
                } catch (Exception e) {

                    e.printStackTrace();
                    response.getWriter().write("Firebase Auth Error: "+ e.getMessage());
                }
            }
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }*/

