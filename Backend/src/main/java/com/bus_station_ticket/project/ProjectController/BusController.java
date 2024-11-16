package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseBoolAndMess;
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
       @Override
       public ResponseEntity<ResponseObject> getAll () {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<BusDTO> listBusEntities = this.busService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listBusEntities.isEmpty() == false) {

                     responseObject.setStatus(MESS_SUCCESS); // set status
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
       @Override
       public ResponseEntity<ResponseObject> getById(@Valid @PathVariable("busId") Long id) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // kiểm tra giá trị id
              if(isValidId(id) == false){
                     responseObject.setStatus(MESS_FAILURE);
                     responseObject.addMessage("mess", "Incorrect path variable value");     
                     responseObject.setData(id);
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
              }

              // Lấy đối tượng AccountEntity dựa vào username
              BusDTO busDTO = busService.getById_toDTO(id);


              // kiểm tra
              if (busDTO != null) {
                     responseObject.setStatus(MESS_SUCCESS);
                     responseObject.setData(busDTO);
                     responseObject.addMessage("mess", "Found data with matching bus id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("buses", "{busId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus(MESS_FAILURE);
              responseObject.setData(busDTO);
              responseObject.addMessage("mess", "No bus entity found with matching bus id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);

       }


       @DeleteMapping("/delete/{busId}")
       @Override
       public ResponseEntity<ResponseObject> delete(@PathVariable("busId") Long id) {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // kiểm tra giá trị id
              if(isValidId(id) == false){
                     responseObject.setStatus(MESS_FAILURE);
                     responseObject.addMessage("mess", "Missing path variable value or incorrect path variable value");     
                     responseObject.setData(id);
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
              }

              // Lấy đối tượng AccountEntity dựa vào username
              BusDTO busDTO = busService.getById_toDTO(id);

              // xóa đối tượng
              ResponseBoolAndMess responseBoolAndMess = busService.delete(id);

              // kiểm tra
              if (responseBoolAndMess.getValueBool()) {
                     responseObject.setStatus(MESS_SUCCESS);
                     responseObject.setData(busDTO);
                     responseObject.addMessage("mess", responseBoolAndMess.getValueMess());

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("buses", "{busId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus(MESS_FAILURE);
              responseObject.setData(busDTO);
              responseObject.addMessage("mess", responseBoolAndMess.getValueMess());

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }


       @PostMapping("/insert")
       @Override
       public ResponseEntity<ResponseObject> save(@Valid BusDTO obj) {
              ResponseObject responseObject = new ResponseObject();

              ResponseBoolAndMess responseBoolAndMess = this.busService.save_toDTO(obj);

              if (responseBoolAndMess.getValueBool()) {
                     responseObject.setStatus(MESS_SUCCESS);
                     responseObject.setData(obj);
                     responseObject.addMessage("mess", responseBoolAndMess.getValueMess());
                     
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("busId", "{busId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus(MESS_FAILURE);
              responseObject.setData(obj);
              responseObject.addMessage("mess", responseBoolAndMess.getValueMess());


              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }


       @PutMapping("/update")
       @Override
       public ResponseEntity<ResponseObject> update(@Valid BusDTO obj) {
              ResponseObject responseObject = new ResponseObject();

              ResponseBoolAndMess responseBoolAndMess = this.busService.update_toDTO(obj);

              if (responseBoolAndMess.getValueBool()) {
                     responseObject.setStatus(MESS_SUCCESS);
                     responseObject.setData(obj);
                     responseObject.addMessage("mess", responseBoolAndMess.getValueMess());
                     
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("buses", "{busId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus(MESS_FAILURE);
              responseObject.setData(obj);
              responseObject.addMessage("mess", responseBoolAndMess.getValueMess());


              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }


       @DeleteMapping("/hidden/{busId}")
       @Override
       public ResponseEntity<ResponseObject> hidden(@PathVariable("busId") Long id) {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // kiểm tra giá trị id
              if(isValidId(id) == false){
                     responseObject.setStatus(MESS_FAILURE);
                     responseObject.addMessage("mess", "Missing path variable value or incorrect path variable value");     
                     responseObject.setData(id);
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
              }

              // ẩn đối tượng
              ResponseBoolAndMess responseBoolAndMess = busService.invisibleWithoutDelete(id);

              
              BusDTO busDTO = busService.getById_toDTO(id);

              // kiểm tra
              if (responseBoolAndMess.getValueBool()) {
                     responseObject.setStatus(MESS_SUCCESS);
                     responseObject.setData(busDTO);
                     responseObject.addMessage("mess", responseBoolAndMess.getValueMess());

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("buses", "{busId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus(MESS_FAILURE);
              responseObject.setData(busDTO);
              responseObject.addMessage("mess", responseBoolAndMess.getValueMess());

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }

       

       

}
