package jihye.backend_mock_exam.config.security;

import jihye.backend_mock_exam.service.auth.CustomRememberMeFilter;
import jihye.backend_mock_exam.service.auth.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/css/**", "/js/**", "/img/**", "/fragment/**").permitAll()
                .requestMatchers("/", "/auth/**", "/exam/**", "/memo/**").permitAll()
                .requestMatchers("/users/**", "/incorrectNote/**", "/history/**", "/myQuestions/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        )

        .formLogin(login -> login
                .loginPage("/auth/sign-in")
                .loginProcessingUrl("/auth/sign-in")
                .failureUrl("/auth/sign-in?error=true")
                .defaultSuccessUrl("/", true)
                .usernameParameter("accountId")
                .passwordParameter("password")
                .permitAll()
        )

        .rememberMe(rememberMe -> rememberMe
                .tokenValiditySeconds(86400)  // 24시간 동안 로그인 유지
                .key("uniqueAndSecret")  // 암호화 키 (같은 값을 사용해야 동일한 사용자 정보로 유지)
        )

        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
        );

        http.csrf(AbstractHttpConfigurer::disable); // 개발중 CSRF 보호 비활성화 (JQuery 에러)
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    // PasswordEncoder Bean 정의
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 정의
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }



}
