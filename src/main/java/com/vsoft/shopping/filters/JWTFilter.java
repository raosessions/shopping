package com.vsoft.shopping.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vsoft.shopping.service.UserService;
import com.vsoft.shopping.utils.JWTUtil;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	JWTUtil jwtUtil;

	@Autowired
	UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorization = request.getHeader("Authorization");

		if (authorization != null) {
			String email = jwtUtil.verify(authorization);
			if (email != null) {
				UserDetails userDetails = this.userService.loadUserByUsername(email);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				if (email != null) {
					System.out.println("Got a valid token.." + email);
				}
			}
		}

		filterChain.doFilter(request, response);
	}

}
