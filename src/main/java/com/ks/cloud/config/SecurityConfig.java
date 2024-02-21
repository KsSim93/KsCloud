package com.ks.cloud.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/swagger-ui/**").hasRole("ADMIN")
                                .requestMatchers("/kscloud/**").hasAnyRole("ADMIN","USER")
                                .anyRequest().permitAll()	// 어떠한 요청이라도 인증필요없음.
                )
                .formLogin(login -> login	// form 방식 로그인 사용
                                .defaultSuccessUrl("/", true)	// 성공 시 메인
                                .successHandler(successHandler())
                                .loginPage("/login")
                                .loginProcessingUrl("/api/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .permitAll()
                )
                .logout(logout -> logout	// 로그아웃 설정
                                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")) // 로그아웃 URL 설정
                                .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 페이지
                                .invalidateHttpSession(true) // HTTP 세션 무효화
                                .deleteCookies("JSESSIONID") // 쿠키 삭제
              /*  )
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(new AuthenticationEntryPoint() {
                            @Override
                            public void commence(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException authException) throws IOException, ServletException {
                                response.sendRedirect("/index.html");
                            }
                        })//401 에러 핸들링
                        .accessDeniedHandler(new AccessDeniedHandler() {
                            @Override
                            public void handle(HttpServletRequest request, HttpServletResponse response,
                                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
                                response.sendRedirect("/index.html");
                            }
                        }) // 403 에러 핸들링
                        */
                );
		 return http.build();
	}

	@Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            private HttpSessionRequestCache requestCache = new HttpSessionRequestCache();

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                SavedRequest savedRequest = requestCache.getRequest(request, response);
                if (savedRequest != null && savedRequest.getRedirectUrl().contains("/swagger-ui")) {
                    super.setDefaultTargetUrl(savedRequest.getRedirectUrl());
                } else {
                    super.setDefaultTargetUrl("/");
                }
                super.onAuthenticationSuccess(request, response, authentication);
            }
        };
    }
}
