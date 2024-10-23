package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.EmployeeDTO;
import com.bus_station_ticket.project.ProjectService.EmployeeService;

@RestController
public class EmployeeController {
       
       private EmployeeService employeeService;

       @Autowired
       public EmployeeController(EmployeeService employeeService) {
              this.employeeService = employeeService;
       }

       // Lấy tất cả các EmployeeEntity có
       // path: "/employees"

       @GetMapping("/employees")
       public ResponseEntity<ResponseObject> getAll() {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<EmployeeDTO> listEmployeeEntities = this.employeeService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listEmployeeEntities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listEmployeeEntities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listEmployeeEntities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("employees", "{driverId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listEmployeeEntities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listEmployeeEntities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }


       // Lấy đối tượng EmployeeEntity dựa vào driverId
       // path: "/employees/{driverId}"

       @GetMapping("/employees/{driverId}")
       public ResponseEntity<ResponseObject> getByRoutesId(@PathVariable("driverId") Long driverId) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              EmployeeDTO employeeDTO = this.employeeService.getByDriverId_toDTO(driverId);

              // kiểm tra
              if (employeeDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(employeeDTO);
                     responseObject.addMessage("mess", "Found data with matching driver id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("employees", "{driverId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(employeeDTO);
              responseObject.addMessage("mess", "No employee entity found with matching driver id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }
       
}
