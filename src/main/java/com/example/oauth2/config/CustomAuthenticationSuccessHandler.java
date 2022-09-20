package com.example.oauth2.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.oauth2.domain.MemberAuthDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication
	) throws IOException {
		var memberAuthDTO = (MemberAuthDTO)authentication.getPrincipal();
		log.info("Succeed to sign in: {}", memberAuthDTO.getEmail());

		response.sendRedirect("/auth");
	}
}
