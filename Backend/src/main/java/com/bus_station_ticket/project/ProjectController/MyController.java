package com.bus_station_ticket.project.ProjectController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
       
       // say hello
       @GetMapping("/")
       public String sayHello(){
              return "Hello User";
       }
}
