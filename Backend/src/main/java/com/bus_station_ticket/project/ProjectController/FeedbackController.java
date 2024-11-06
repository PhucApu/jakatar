package com.bus_station_ticket.project.ProjectController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bus_station_ticket.project.ProjectConfig.ResponseObject;

import com.bus_station_ticket.project.ProjectDTO.FeedbackDTO;

import com.bus_station_ticket.project.ProjectService.FeedbackService;

@RestController
public class FeedbackController {

       @Autowired
       private FeedbackService feedbackService;


       // Lấy tất cả các FeedbackEntity có
       // path: "/feedbacks"

       @GetMapping("/feedbacks")
       public ResponseEntity<ResponseObject> getAll() {
              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu từ lớp Service
              List<FeedbackDTO> listFeedbackentities = this.feedbackService.getAll_toDTO();

              // kiểm tra
              // Nếu mảng không rỗng
              if (listFeedbackentities.isEmpty() == false) {

                     responseObject.setStatus("success"); // set status
                     responseObject.setData(listFeedbackentities); // set data

                     responseObject.addMessage("mess", "Successfully retrieved data");
                     responseObject.addMessage("length", listFeedbackentities.size());
                     responseObject.addMessage("info", responseObject.getPathBasicInfor("feedbacks", "{feedbackId}"));

                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);

              }
              responseObject.setStatus("failure");
              responseObject.setData(listFeedbackentities);
              responseObject.addMessage("mess", "There is no data in the database");
              responseObject.addMessage("length", listFeedbackentities.size());

              return ResponseEntity.status(HttpStatus.OK).body(responseObject);
       }

       // Lấy đối tượng FeedbackEntity dựa vào feedbackId
       // path: "/feedbacks/{feedbackId}"

       @GetMapping("/feedbacks/{feedbackId}")
       public ResponseEntity<ResponseObject> getByRoutesId(@PathVariable("feedbackId") Long feedbackId) {

              // Tạo một đối tượng phản hồi ResponseObject
              ResponseObject responseObject = new ResponseObject();

              // Lấy đối tượng AccountEntity dựa vào username
              FeedbackDTO feedbackDTO = this.feedbackService.getById_toDTO(feedbackId);

              // kiểm tra
              if (feedbackDTO != null) {
                     responseObject.setStatus("success");
                     responseObject.setData(feedbackDTO);
                     responseObject.addMessage("mess", "Found data with matching feedback id");

                     responseObject.addMessage("info", responseObject.getPathBasicInfor("feedbacks", "{feedbackId}"));
                     return ResponseEntity.status(HttpStatus.OK).body(responseObject);
              }

              responseObject.setStatus("failure");
              responseObject.setData(feedbackDTO);
              responseObject.addMessage("mess", "No feedback entity found with matching feedback id");

              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
       }

       
}
