package com.bus_station_ticket.project.ProjectExceptionHandler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;

@ControllerAdvice
public class ExceptionBadRequestHandler {
       
       @ExceptionHandler(MethodArgumentTypeMismatchException.class)
       public ResponseEntity<ResponseObject> handler (MethodArgumentTypeMismatchException ex){
              
              ResponseObject responseObject = new ResponseObject();
              responseObject.setStatus("failure");
              responseObject.addMessage("mess", ex.getMessage());
              responseObject.setData(ex.getStackTrace());

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }
}
