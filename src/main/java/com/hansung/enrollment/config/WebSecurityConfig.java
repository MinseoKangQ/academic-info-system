package com.hansung.enrollment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**"); // 정적 리소스에 대한 접근 허용
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable) // cors 비활성화
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/login", "/css/**", "/js/**", "/images/**").permitAll()  // '/login' 및 정적 리소스에 대한 접근 허용
                        .requestMatchers("/credits", "/enroll", "/enrollments", "enrollments/**", "/detail-credits/**").hasAuthority("ROLE_USER")  // 학년별 이수 학점 조회, 수강 신청하기, 수강 신청 조회에 대한 권한 설정
                        .anyRequest().authenticated()  // 나머지 모든 요청은 인증을 요구함
                )
                .formLogin(form -> form // 로그인
                        .loginPage("/login").permitAll()  // 사용자 정의 로그인 페이지 지정
                        .loginProcessingUrl("/login").permitAll()  // 모든 사용자가 로그인 페이지 접근을 허용
                        .successHandler(loginSuccessHandler()) // 로그인 성공 핸들러 추가
                        .failureHandler(loginFailureHandler())
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리디렉션될 경로 지정
                        .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 삭제
                        .invalidateHttpSession(true) // 세션 무효화
                )
                .build();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) -> {
            System.out.println("success"); // 로그인 성공 시 콘솔에 메시지 출력
            response.sendRedirect("/"); // 로그인 성공 후 리디렉션
        };
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println("Login failed for username: " + username + ", password: " + password); // 로그인 실패 시 콘솔에 메시지 출력
            response.sendRedirect("/login?error=true"); // 로그인 실패 후 리디렉션 경로
        };
    }
}
