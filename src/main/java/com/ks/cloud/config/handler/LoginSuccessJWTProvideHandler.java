package com.ks.cloud.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginSuccessJWTProvideHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
                                        , Authentication authentication) throws IOException, ServletException {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("[Login] Success login. Username : {}",userDetails.getUsername());

        response.getWriter().write("success");
    }
}
