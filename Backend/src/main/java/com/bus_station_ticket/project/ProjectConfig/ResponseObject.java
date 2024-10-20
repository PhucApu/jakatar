package com.bus_station_ticket.project.ProjectConfig;

// Đây là là lớp được dùng để trả về một đối tượng phản hồi chuẩn RestAPI
import java.util.HashMap;
import java.util.Map;

// Đây là lớp được dùng để tùy chỉnh phản hồi trả về người dùng theo chuẩn RestAPI
public class ResponseObject {

       private String status; // Trạng thái phản hồi HTTP

       private Map<String, Object> message; // các thông báo cần thiết khác

       private Object data; // dữ liệu trả về

       // Contructor
       public ResponseObject(String status, Map<String, Object> message, Object data) {
              this.status = status;
              this.message = new HashMap<>();
              this.data = data;
       }

       public ResponseObject() {
              this.message = new HashMap<>();
       }

       // GETTER và SETTER
       public String getStatus() {
              return status;
       }

       public void setStatus(String status) {
              this.status = status;
       }

       public Map<String, Object> getMessage() {
              return message;
       }

       public void setMessage(Map<String, Object> message) {
              this.message = message;
       }

       public Object getData() {
              return data;
       }

       public void setData(Object data) {
              this.data = data;
       }

       // add message
       public void addMessage(String key, Object value){
              this.message.put(key, value);
       }
}