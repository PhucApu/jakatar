package com.bus_station_ticket.project.ProjectConfig;

// Được dùng để phản hồi kết quả Boolean kèm tin nhắn Mess
public class ResponseBoolAndMess {
       
       private Boolean valueBool;
       private String valueMess;
       public ResponseBoolAndMess(Boolean valueBool, String valueMess) {
              this.valueBool = valueBool;
              this.valueMess = valueMess;
       }
       public Boolean getValueBool() {
              return valueBool;
       }
       public void setValueBool(Boolean valueBool) {
              this.valueBool = valueBool;
       }
       public String getValueMess() {
              return valueMess;
       }
       public void setValueMess(String valueMess) {
              this.valueMess = valueMess;
       }

}
