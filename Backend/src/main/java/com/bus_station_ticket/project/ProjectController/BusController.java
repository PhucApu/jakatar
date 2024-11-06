package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.BusDTO;
import com.bus_station_ticket.project.ProjectService.BusService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/buses")
public class BusController implements RestApiSimpleControllerInf<BusDTO,Long> {

       @Autowired
       private BusService  busService;


       // Lấy tất cả các BusEntity có
       // path: "/buses"

       @GetMapping
       public ResponseEntity<ResponseObject> getAll () {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<BusDTO> listBusEntities = this.busService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listBusEntities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listBusEntities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listBusEntities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("buses", "{busId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listBusEntities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listBusEntities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }


       // lấy đối tượng BusEntity dựa vào busId
       // path: "/buses/{busId}"
       @GetMapping("/{busId}")
       public ResponseEntity<ResponseObject> getById(@Valid @PathVariable("busId") Long busId) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              BusDTO busDTO = busService.getById_toDTO(busId);

              // kiểm tra
              if (busDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(busDTO);
                     responseObject.addMessage("mess", "Found data with matching bus id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("buses", "{busId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(busDTO);
              responseObject.addMessage("mess", "No bus entity found with matching bus id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);

       }


       @Override
       public ResponseEntity<ResponseObject> delete(Long id) {
              // TODO Auto-generated method stub
              return null;
       }


       @Override
       public ResponseEntity<ResponseObject> save(@Valid BusDTO obj) {
              // TODO Auto-generated method stub
              return null;
       }


       @Override
       public ResponseEntity<ResponseObject> update(@Valid BusDTO obj) {
              // TODO Auto-generated method stub
              return null;
       }

       

}
