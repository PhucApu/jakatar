package com.bus_station_ticket.project.ProjectSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bus_station_ticket.project.ProjectConfig.WebConfig;
import com.bus_station_ticket.project.ProjectExceptionHandler.ExceptionAccessDeniedHandler;
import com.bus_station_ticket.project.ProjectExceptionHandler.ExceptionAuthenticationEntryPoint;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

       @Autowired
       private JwtFilter jwtFilter;

       private final String[] ADMIN_ACCESS_PATH = {
                     "/accounts/**",
                     "/buses/**",
                     "/busroutes/**",
                     "/discounts/**",
                     "/employees/**",
                     "/feedbacks/**",
                     "/payments/**",
                     "/penaltytickets/**",
                     "/tickets/**"
       };

       private final String[] MANAGER_ACCESS_PATH = {

                     "/buses",
                     "/buses/{busId}",
                     "/buses/update",

                     "/busroutes",
                     "/busroutes/{routesId}",

                     "/employees",
                     "/employees/{driverId}",
                     "/employees/update",

                     "/feedbacks",
                     "/feedbacks/{feedbackId}",
                     "/feedbacks/update",
                     "/feedbacks/hidden",

                     "/tickets/**"
       };

       // Cấu hình các bộ lọc Filter trong SecurityFilterChain cho việc bảo mật và xác
       // thực
       @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              http
                            .cors(cors -> cors.configurationSource(new WebConfig()))
                            // kích hoạt xác thực bằng HTTP Basic Filter
                            .httpBasic(request -> request
                                          .authenticationEntryPoint(new ExceptionAuthenticationEntryPoint())

                            )
                            // Cấu hình phân quyền
                            .authorizeHttpRequests(request -> request
                                          .requestMatchers(ADMIN_ACCESS_PATH)
                                          .hasAnyRole("ADMIN")
                                          // .requestMatchers("/datas", "/user").hasAnyRole("USER", "ADMIN")
                                          .anyRequest()
                                          .authenticated())
                            .exceptionHandling(
                                          request -> request.accessDeniedHandler(new ExceptionAccessDeniedHandler())
                                                        .authenticationEntryPoint(
                                                                      new ExceptionAuthenticationEntryPoint()))

                            // Thêm filter JWT trước Basic Authentication
                            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                            // vô hiệu hóa bảo mật bằng CSRF Filter
                            .csrf(request -> request.disable())
                            // vô hiệu hóa bảo mật bằng Form
                            .formLogin(request -> request.disable());

              return http.build();
       }

       // Khởi tạo một AuthenticationManager và đăng ký một AuthenticationProvider
       @Bean
       public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService)
                     throws Exception {
              return http
                            .getSharedObject(AuthenticationManagerBuilder.class)
                            .authenticationProvider(authenticationProvider(userDetailsService))
                            .build();

       }

       // Triển khai một AuthencticationProvider với xác thực bằng
       // DaoAuthenticationProvider
       @Bean
       public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
              DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
              dao.setUserDetailsService(userDetailsService);
              // Dùng mật khẩu mã hóa BCryptPasswordEncoder với mức độ mã hóa là 10
              dao.setPasswordEncoder(new BCryptPasswordEncoder(10));
              return dao;
       }

       // Khởi tạo một Bean UserDetailService liên kết tự động với
       // DaoAuthenticationProvider để xác thực
       @Bean
       public UserDetailsService userDetailsService(AccountRepo accountRepo) {
              return new UserDetailsServiceConfig(accountRepo);
       }

       @Bean
       public ExceptionAccessDeniedHandler exceptionAccessDeniedHandler() {
              return new ExceptionAccessDeniedHandler();
       }

       @Bean
       public ExceptionAuthenticationEntryPoint exceptionAuthenticationEntryPoint() {
              return new ExceptionAuthenticationEntryPoint();
       }

       @Bean
       public CorsConfigurationSource configurationSource() {
              return new WebConfig();
       }
}
