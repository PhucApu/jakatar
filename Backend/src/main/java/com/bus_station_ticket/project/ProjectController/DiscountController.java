package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.DiscountDTO;
import com.bus_station_ticket.project.ProjectService.DiscountService;

@RestController
public class DiscountController {

       @Autowired
       private DiscountService discountService;


       // Lấy tất cả các DiscountEntity có
       // path: "/discounts"

       @GetMapping("/discounts")
       public ResponseEntity<ResponseObject> getAll() {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<DiscountDTO> listDiscountEntities = this.discountService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listDiscountEntities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listDiscountEntities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listDiscountEntities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("discounts", "{discountId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listDiscountEntities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listDiscountEntities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }

       // Lấy đối tượng DiscountEntity dựa vào discountId
       // path: "/discounts/{discountId}"

       @GetMapping("/discounts/{discountId}")
       public ResponseEntity<ResponseObject> getByRoutesId(@PathVariable("discountId") Long discountId) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              DiscountDTO discountDTO = this.discountService.getByDiscountId_toDTO(discountId);

              // kiểm tra
              if (discountDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(discountDTO);
                     responseObject.addMessage("mess", "Found data with matching discount id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("discounts", "{discountId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(discountDTO);
              responseObject.addMessage("mess", "No discount entity found with matching discount id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }

}