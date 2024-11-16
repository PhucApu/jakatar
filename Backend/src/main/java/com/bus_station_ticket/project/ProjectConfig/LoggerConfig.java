package com.bus_station_ticket.project.ProjectConfig;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerConfig {

       private Class<?> clazz;

       private Logger logger;

       private String userName;


       public LoggerConfig(Class<?> clazz, String userNamee) {
              this.logger = LoggerFactory.getLogger(clazz);
              this.userName = userNamee;
       }

       // Ghi log dữ liệu, mức độ Info
       public void writeInfoLevel(String mess,String methodName) {

              // Lấy thời gian hiện tại
              LocalDateTime presenTime = LocalDateTime.now();

              String messInfo = "[" + userName + "][" + presenTime + "][" + clazz.getName() + "]["+ methodName +"]: " + mess;

              this.logger.info(messInfo);
       }

       // Ghi log dữ liệu, mức độ Warning
       public void writeWarningLevel(String mess, String methodName) {

              // Lấy thời gian hiện tại
              LocalDateTime presenTime = LocalDateTime.now();

              String messInfo = "[" + userName + "][" + presenTime + "][" + clazz.getName() + "]["+ methodName +"]: " + mess;

              this.logger.warn(messInfo);
       }

       // Ghi log dữ liệu, mức độ Error
       public void writeErrorLevel(String mess, String methodName) {

              // Lấy thời gian hiện tại
              LocalDateTime presenTime = LocalDateTime.now();

              String messInfo = "[" + userName + "][" + presenTime + "][" + clazz.getName() + "]["+ methodName +"]: " + mess;

              this.logger.error(messInfo);
       }

}
