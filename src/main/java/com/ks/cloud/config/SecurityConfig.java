package com.ks.cloud.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ks.cloud.config.filter.CustomAuthenticationFilter;
import com.ks.cloud.config.handler.LoginFailureHandler;
import com.ks.cloud.config.handler.LoginSuccessJWTProvideHandler;
import com.ks.cloud.security.UserDetailsServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;
	private final ObjectMapper objectMapper;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                 //               .requestMatchers("/swagger-ui/**").hasRole("ADMIN")
                 //               .requestMatchers("/kscloud/**").hasAnyRole("ADMIN","USER")
                                .anyRequest().permitAll()	// 어떠한 요청이라도 인증필요없음.
                )
                .formLogin(AbstractHttpConfigurer::disable)
				.addFilterAfter(customLoginFilter(), LogoutFilter.class) // 추가 : 커스터마이징 된 필터를 SpringSecurityFilterChain에 등록
                .logout(logout -> logout	// 로그아웃 설정
                                .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")) // 로그아웃 URL 설정
                                .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 페이지
                                .invalidateHttpSession(true) // HTTP 세션 무효화
                                .deleteCookies("JSESSIONID") // 쿠키 삭제
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

	// 인증 관리자 관련 설정
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

		return daoAuthenticationProvider;
	}

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider authProvider = daoAuthenticationProvider();//DaoAuthenticationProvider 사용
        //authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler(){
        return new LoginSuccessJWTProvideHandler();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    @Bean
	public CustomAuthenticationFilter customLoginFilter() throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(objectMapper);
		customAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessJWTProvideHandler());
		customAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler());

		return customAuthenticationFilter;
	}

}
