package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectService.AccountService;

@Controller
public class AccountController {

       @Autowired
       private AccountService accountService;


       // Lấy tất cả các AccountEntity có
       // path: "/accounts"
       @GetMapping("/accounts")
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
       @GetMapping("/accounts/{username-id}")
       public ResponseEntity<ResponseObject> getByUserName(@PathVariable("username-id") String userName) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              AccountDTO accountDTO = accountService.getByUserName_toDTO(userName);

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

}
