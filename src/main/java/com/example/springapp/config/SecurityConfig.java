// package com.example.springapp.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @org.springframework.context.annotation.Configuration
// public class SecurityConfig {
//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/h2-console/**").permitAll()
//                 .requestMatchers("/api/public/**").permitAll() // 公開API
//                 .anyRequest().authenticated()
//             )
//             .formLogin(withDefaults())
//             .httpBasic(withDefaults());
//         return http.build();
//     }
// }
