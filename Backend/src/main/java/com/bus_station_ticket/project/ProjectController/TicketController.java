package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.TicketDTO;
import com.bus_station_ticket.project.ProjectService.TicketService;

@RestController
public class TicketController {
       
       @Autowired
       private TicketService ticketService;

       @GetMapping("/tickets")
       public ResponseEntity<ResponseObject> getAll() {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<TicketDTO> listTicketEntities = this.ticketService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listTicketEntities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listTicketEntities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listTicketEntities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("tickets", "{ticketId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listTicketEntities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listTicketEntities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }

       // Lấy đối tượng TicketEntity dựa vào ticketId
       // path: "/tickets/{ticketId}"

       @GetMapping("/tickets/{ticketId}")
       public ResponseEntity<ResponseObject> getByRoutesId(@PathVariable("ticketId") Long ticketId) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              TicketDTO ticketDTO = this.ticketService.getById_toDTO(ticketId);

              // kiểm tra
              if (ticketDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(ticketDTO);
                     responseObject.addMessage("mess", "Found data with matching ticket id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("tickets", "{ticketId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(ticketDTO);
              responseObject.addMessage("mess", "No ticket entity found with matching ticket id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }
}
