package com.bus_station_ticket.project.ProjectController;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.PaymentDTO;
import com.bus_station_ticket.project.ProjectService.PaymentService;

@RestController
public class PaymentController {
       
       @Autowired
       private PaymentService paymentService;


       // Lấy tất cả các PaymentEntity có
       // path: "/payments"

       @GetMapping("/payments")
       public ResponseEntity<ResponseObject> getAll() {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<PaymentDTO> listPaymentEntities = this.paymentService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listPaymentEntities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listPaymentEntities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listPaymentEntities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("payments", "{paymentId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listPaymentEntities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listPaymentEntities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }

       // Lấy đối tượng PaymentEntity dựa vào payemntId
       // path: "/payments/{paymentId}"

       @GetMapping("/payments/{paymentId}")
       public ResponseEntity<ResponseObject> getByRoutesId(@PathVariable("paymentId") Long paymentId) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              PaymentDTO paymentDTO = this.paymentService.getByPaymentId_toDTO(paymentId);

              // kiểm tra
              if (paymentDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(paymentDTO);
                     responseObject.addMessage("mess", "Found data with matching payment id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("payments", "{paymentId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(paymentDTO);
              responseObject.addMessage("mess", "No payment entity found with matching payment id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }
       
}
