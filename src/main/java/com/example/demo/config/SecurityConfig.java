package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * パスワードエンコーダーの定義
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * セキュリティフィルターチェーンの設定
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // 静的リソースは認証不要
                .requestMatchers("/login").permitAll() // ログインページは認証不要
                .requestMatchers("/register").permitAll()
                .anyRequest().authenticated() // その他のページは認証が必要
            )
            .formLogin(form -> form
                .loginPage("/login") // カスタムログインページを指定
                .loginProcessingUrl("/login") // ログイン処理のURL
                .usernameParameter("username") // ユーザー名のパラメータ名
                .passwordParameter("password") // パスワードのパラメータ名
                .defaultSuccessUrl("/userList", true) // ログイン成功時のリダイレクト先
                .failureUrl("/login?error=true") // ログイン失敗時のリダイレクト先
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // ログアウトURL
                .logoutSuccessUrl("/login?logout=true") // ログアウト成功時のリダイレクト先
                .invalidateHttpSession(true) // セッションを無効化
                .deleteCookies("JSESSIONID") // Cookieを削除
                .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1) // 同時セッション数を制限
                .maxSessionsPreventsLogin(false) // 新しいログインを優先
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
            .username("user")
            .password(passwordEncoder().encode("password"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}