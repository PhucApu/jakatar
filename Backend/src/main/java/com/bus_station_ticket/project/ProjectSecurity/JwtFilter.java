package com.bus_station_ticket.project.ProjectSecurity;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

       @Autowired
       private JwtService jwtService;

       @Autowired
       private ApplicationContext context;

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                     FilterChain filterChain) throws ServletException, IOException {

              // Bearer
              // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcHUiLCJpYXQiOjE3MzE3NTAxMTUsImV4cCI6MTczMTc1MDIyM30.ogmh8er6-eU8A2uehpoHbeid2st_CtexmY9RlV0Uf1g

              String authHeeader = request.getHeader("Authorization");
              String token = null;
              ;
              String username = null;

              if (authHeeader != null && authHeeader.startsWith("Bearer ")) {
                     token = authHeeader.substring(7);
                     username = jwtService.extractUserName(token);
              }

              if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                     UserDetails userDetails = context.getBean(UserDetailsServiceConfig.class).loadUserByUsername(username);

                     if (jwtService.validateToken(token, userDetails)) {
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                                          userDetails, null, userDetails.getAuthorities());

                            usernamePasswordAuthenticationToken
                                          .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                     }
              }
              filterChain.doFilter(request, response);

       }

}
