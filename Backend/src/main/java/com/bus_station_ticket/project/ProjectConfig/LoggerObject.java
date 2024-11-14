package com.bus_station_ticket.project.ProjectConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bus_station_ticket.project.ProjectController.AccountController;

public class LoggerObject {

       private Class<?> clazz;

       private Logger logger;

       private String userName;



       public LoggerObject(Class<?> clazz, String userNamee) {
              this.logger = LoggerFactory.getLogger(clazz);
              this.userName = userNamee;
       }

       // Ghi log tri suất dữ liệu
}
