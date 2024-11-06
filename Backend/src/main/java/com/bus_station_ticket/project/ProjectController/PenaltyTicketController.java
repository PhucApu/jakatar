package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.PenaltyTicketDTO;
import com.bus_station_ticket.project.ProjectService.PenaltyTicketService;

@RestController
public class PenaltyTicketController {
       

       @Autowired
       private PenaltyTicketService penaltyTicketService;

       // Lấy tất cả các PenaltyTicketEntity có
       // path: "/penaltytickets"

       @GetMapping("/penaltytickets")
       public ResponseEntity<ResponseObject> getAll() {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<PenaltyTicketDTO> listPenaltyTicketEntities = this.penaltyTicketService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listPenaltyTicketEntities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listPenaltyTicketEntities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listPenaltyTicketEntities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("penaltytickets", "{penaltyTicketId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listPenaltyTicketEntities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listPenaltyTicketEntities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }

       // Lấy đối tượng PenaltyTicketEntity dựa vào penaltyTicketId
       // path: "/penaltytickets/{penaltyTicketId}"

       @GetMapping("/penaltytickets/{penaltyTicketId}")
       public ResponseEntity<ResponseObject> getByRoutesId(@PathVariable("penaltyTicketId") Long penaltyTicketId) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              PenaltyTicketDTO penaltyTicketDTO = this.penaltyTicketService.getById_toDTO(penaltyTicketId);

              // kiểm tra
              if (penaltyTicketDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(penaltyTicketDTO);
                     responseObject.addMessage("mess", "Found data with matching penalty ticket id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("penaltytickets", "{penaltyTicketId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(penaltyTicketDTO);
              responseObject.addMessage("mess", "No penalty ticket entity found with matching penalty ticket id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }
}
