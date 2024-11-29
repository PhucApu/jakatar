package com.bus_station_ticket.project.ProjectController;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectService.AccountService;
import com.bus_station_ticket.project.ProjectService.TicketService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class VNPayController {
       
       @Autowired
       private TicketService ticketService;

       @Autowired
       private AccountService accountService;

       @PostMapping("/create_payment")
       public ResponseEntity<ResponseObject> creatPayment(HttpServletRequest request,
                     @RequestParam("returnUrl") String returnUrl, @RequestParam("seat") String seat,
                     @RequestParam("busId") Long busId, @RequestParam("departureLocation") String departureLocation,
                     @RequestParam("destinationLocation") String destinationLocation,
                     @RequestParam("departureTime") LocalDateTime departureTime,
                     @RequestParam("arivalTime") LocalDateTime arivalTime, @RequestParam("discountId") Long discountId,
                     @RequestParam("token") String token) {

              try {
                     String baseUrl = request.getScheme() + "://" + request.getServerName() + ":"
                                   + request.getServerPort() + "/vnpay-payment-return";

                     ResponseObject responseObject = this.ticketService.createTicketAndPayment(request, baseUrl, seat,
                                   busId, departureLocation, destinationLocation, departureTime, arivalTime, discountId,
                                   token);

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              } catch (Exception e) {
                     e.printStackTrace();
                     ResponseObject responseObject = new ResponseObject();
                     responseObject.setStatus("success");
                     responseObject.addMessage("mess", "Exception '/create_payment' ");
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
              }
       }

       @GetMapping("/vnpay-payment-return")
       public ResponseEntity<ResponseObject> resultPayment(HttpServletRequest request) {

              if (this.accountService.geAccountDTOHasLogin() != null) {
                     ResponseObject responseObject = this.ticketService.returnFronVNPay(request);

                     if (responseObject.getStatus().equals("success")) {
                            return ResponseEntity.status(HttpStatus.OK).body(responseObject);
                     }
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
              }
              ResponseObject responseObject = new ResponseObject();
              responseObject.setStatus("failure");
              responseObject.setData("400 - UNAUTHORIZED");
              responseObject.addMessage("mess",
                            "Unauthorized - Your information is wrong. Please check username and password");

              return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseObject);
       }
}
