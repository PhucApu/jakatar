package com.bus_station_ticket.project.ProjectExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;

@ControllerAdvice
public class ExceptionValidateHandler {

       @ExceptionHandler(MethodArgumentNotValidException.class)
       public ResponseEntity<ResponseObject> handler(MethodArgumentNotValidException ex) {
              ResponseObject responseObject = new ResponseObject();
              responseObject.setStatus("failure");
              responseObject.addMessage("mess", "Input data does not meet the conditions in " + ex.getObjectName());

              ex.getBindingResult().getAllErrors().forEach((error) -> {
                     String fieldName = ((FieldError) error).getField();
                     String errorMessage = error.getDefaultMessage();
                     responseObject.addMessage(fieldName, errorMessage);
              });
              
              responseObject.setData(ex.getTarget());
              
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }
}



