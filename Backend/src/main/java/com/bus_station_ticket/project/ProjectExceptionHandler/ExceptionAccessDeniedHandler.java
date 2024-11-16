package com.bus_station_ticket.project.ProjectExceptionHandler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ExceptionAccessDeniedHandler implements AccessDeniedHandler {

       @Override
       public void handle(HttpServletRequest request, HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException, ServletException {
                            
              // Trả về lỗi 403(Không đủ quyền truy cập tài nguyên) với thông báo tùy chỉnh
              response.setStatus(HttpStatus.FORBIDDEN.value());
              response.setContentType("application/json");

              ResponseObject responseObject = new ResponseObject();
              responseObject.setStatus("failure");
              responseObject.setData("403 - FORBIDDEN");
              responseObject.addMessage("mess",
                            "Forbidden - You don't have permission to access this resource.");
              ObjectMapper objectMapper = new ObjectMapper();
              String show = objectMapper.writeValueAsString(responseObject);

              response.getWriter().write(show);
       }

}
