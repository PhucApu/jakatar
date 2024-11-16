package com.bus_station_ticket.project.ProjectExceptionHandler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionAuthenticationEntryPoint implements AuthenticationEntryPoint {

       @Override
       public void commence(HttpServletRequest request, HttpServletResponse response,
                     AuthenticationException authException) throws IOException, ServletException {

              // Trả về lỗi 401 (Đăng nhập sai username hoặc pass) với thông báo tùy chỉnh

              if (authException instanceof BadCredentialsException) {

                     response.setStatus(HttpStatus.UNAUTHORIZED.value());
                     response.setContentType("application/json");

                     ResponseObject responseObject = new ResponseObject();
                     responseObject.setStatus("failure");
                     responseObject.setData("401 - UNAUTHORIZED");
                     responseObject.addMessage("mess",
                                   "Unauthorized - Your information is wrong. Please check username and password");

                     // Chuyển một đối tượng thành dạng json qua đối tượng ObjectMapper
                     ObjectMapper objectMapper = new ObjectMapper();
                     String show = objectMapper.writeValueAsString(responseObject);

                     response.getWriter().write(show);
              } else {

                     // Chưa đăng nhập

                     response.setStatus(HttpStatus.UNAUTHORIZED.value());
                     response.setContentType("application/json");

                     ResponseObject responseObject = new ResponseObject();
                     responseObject.setStatus("failure");
                     responseObject.setData("401 - UNAUTHORIZED");
                     responseObject.addMessage("mess",
                                   "Unauthorized - You need to login first.");
                     responseObject.addMessage("exception",
                                   authException.toString());

                     // Chuyển một đối tượng thành dạng json qua đối tượng ObjectMapper
                     ObjectMapper objectMapper = new ObjectMapper();
                     String show = objectMapper.writeValueAsString(responseObject);

                     response.getWriter().write(show);
              }

       }

}
