package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;

import com.bus_station_ticket.project.ProjectDTO.BusRoutesDTO;
import com.bus_station_ticket.project.ProjectService.BusRoutesService;

@RestController
public class BusRoutesController {

       @Autowired
       private BusRoutesService busRoutesService;

       // Lấy tất cả các BusRoutesEntity có
       // path: "/busroutes"
       @GetMapping("/busroutes")
       public ResponseEntity<ResponseObject> getAll() {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<BusRoutesDTO> listBusRoutesEntities = this.busRoutesService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listBusRoutesEntities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listBusRoutesEntities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listBusRoutesEntities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("busroutes", "{routesId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listBusRoutesEntities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listBusRoutesEntities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }

       // Lấy đối tượng BusRoutesEnity dựa vào routesId
       // path: "/busroutes/{routesId}"
       @GetMapping("/busroutes/{routesId}")
       public ResponseEntity<ResponseObject> getByRoutesId(@PathVariable("routesId") Long routesId) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              BusRoutesDTO busRoutesDTO = busRoutesService.getByRoutesId_toDTO(routesId);

              // kiểm tra
              if (busRoutesDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(busRoutesDTO);
                     responseObject.addMessage("mess", "Found data with matching bus routes id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("busroutes", "{routesId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(busRoutesDTO);
              responseObject.addMessage("mess", "No bus routes entity found with matching routes id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }

}
