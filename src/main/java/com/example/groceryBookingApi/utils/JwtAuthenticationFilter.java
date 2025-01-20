package com.example.groceryBookingApi.utils;

import com.example.groceryBookingApi.serviceImpl.UserInfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserInfoService customUserDetailsService;

    public JwtAuthenticationFilter(UserInfoService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = request.getHeader("Authorization");

        if (token != null && JwtUtils.validateToken(token)) {
            String username = JwtUtils.getUsernameFromToken(token);

            // Extract roles from the token (no "ROLE_" prefix)
            List<String> roles = JwtUtils.getRolesFromToken(token);

            // Convert roles to GrantedAuthority objects (no ROLE_ prefix)
            List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)  // No ROLE_ prefix
                    .collect(Collectors.toList());

            // Set authentication
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}

