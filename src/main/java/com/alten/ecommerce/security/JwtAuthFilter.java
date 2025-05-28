package com.alten.ecommerce.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alten.ecommerce.model.User;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.service.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter{
	
	 private final JwtUtil jwtUtil;
	 private final UserRepository userRepository;
	 
	 public JwtAuthFilter(JwtUtil jwtUtil, UserRepository userRepository) {
	        this.jwtUtil = jwtUtil;
	        this.userRepository = userRepository;
	    }
	 
	 protected void doFilterInternal(HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain) throws ServletException, IOException {
		 
		 String header = request.getHeader("Authorization");
		 
		 if (header != null && header.startsWith("Bearer ")) {
			 String token = header.substring(7);
			 
			 if(jwtUtil.validateToken(token)) {
				 String email = jwtUtil.extractEmail(token);
				 User user = userRepository.findByEmail(email).orElse(null);
				 if(user!=null) {
					 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, null, List.of());
					 SecurityContextHolder.getContext().setAuthentication(auth);
				 }
			 }
		 }
		 filterChain.doFilter(request, response);
	 }


}
