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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectService.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController implements RestApiSimpleControllerInf<AccountDTO,String> {

       @Autowired
       private AccountService accountService;

       // Lấy tất cả các AccountEntity có
       // path: "/accounts" http:localhost:8080/accounts
       @GetMapping
       public ResponseEntity<ResponseObject> getAll() {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<AccountDTO> listAccountEnities = this.accountService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listAccountEnities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listAccountEnities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listAccountEnities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("accounts", "{username-id}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listAccountEnities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listAccountEnities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }

       // lấy đối tượng AccountEntity dựa vào username
       // path: "/account/{username-id}"
       @GetMapping("/{username-id}")
       public ResponseEntity<ResponseObject> getById(@PathVariable("username-id") String id) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // kiểm tra giá trị id
              if(isValidId(id) == false){
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Incorrect path variable value");     
                     responseObject.setData(id);
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
              }

              // Lấy đối tượng AccountEntity dựa vào username
              AccountDTO accountDTO = accountService.getById_toDTO(id);

              // kiểm tra
              if (accountDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(accountDTO);
                     responseObject.addMessage("mess", "Found data with matching login name");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("accounts", "{username-id}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(accountDTO);
              responseObject.addMessage("mess", "No user found with matching login name");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);

       }

       // lưu một đối tượng account
       // path: "/insert"
       @PostMapping("/insert")
       public ResponseEntity<ResponseObject> save(@Valid @RequestBody AccountDTO accountDTO) {

              ResponseObject responseObject = new ResponseObject();

              if (this.accountService.save_toDTO(accountDTO)) {
                     responseObject.setStatus("success");
                     responseObject.setData(accountDTO);
                     responseObject.addMessage("mess", "Save data successfully");
                     
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("accounts", "{username-id}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(accountDTO);
              responseObject.addMessage("mess", "Save data not successfully");


              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }

       @DeleteMapping("/delete/{username-id}")
       @Override
       public ResponseEntity<ResponseObject> delete(@PathVariable("username-id") String id) {
              
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // kiểm tra giá trị id
              if(isValidId(id) == false){
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Missing path variable value or incorrect path variable value");     
                     responseObject.setData(id);
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
              }

              // Lấy đối tượng AccountEntity dựa vào username
              AccountDTO accountDTO = accountService.getById_toDTO(id);

              // xóa đối tượng
              Boolean checkDelete = accountService.delete(id);

              // kiểm tra
              if (checkDelete) {
                     responseObject.setStatus("success");
                     responseObject.setData(accountDTO);
                     responseObject.addMessage("mess", "Deleted data with matching login name");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("accounts", "{username-id}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(accountDTO);
              responseObject.addMessage("mess", "No user found with matching login name");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }

       @PutMapping("/update")
       @Override
       public ResponseEntity<ResponseObject> update(@Valid @RequestBody AccountDTO accountDTO) {
              
              ResponseObject responseObject = new ResponseObject();

              if (this.accountService.update_toDTO(accountDTO)) {
                     responseObject.setStatus("success");
                     responseObject.setData(accountDTO);
                     responseObject.addMessage("mess", "Update data successfully");
                     
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("accounts", "{username-id}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(accountDTO);
              responseObject.addMessage("mess", "Update data not successfully");


              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);

       }

       
}
