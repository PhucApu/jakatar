package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;

public class PenaltyTicketDTO {

       private Long penaltyTicketId;
       private Long busEntity_Id;
       private Long employeeEntity_Id;
       private LocalDateTime penaltyDay;
       private String description;
       private Boolean responsibility;
       private float price;
       private Boolean isDelete;

       public PenaltyTicketDTO() {
       }

       public PenaltyTicketDTO(Long penaltyTicketId, Long busEntity_Id, Long employeeEntity_Id,
                     LocalDateTime penaltyDay, String description, Boolean responsibility, float price,
                     Boolean isDelete) {
              this.penaltyTicketId = penaltyTicketId;
              this.busEntity_Id = busEntity_Id;
              this.employeeEntity_Id = employeeEntity_Id;
              this.penaltyDay = penaltyDay;
              this.description = description;
              this.responsibility = responsibility;
              this.price = price;
              this.isDelete = isDelete;
       }

       public Long getPenaltyTicketId() {
              return penaltyTicketId;
       }

       public void setPenaltyTicketId(Long penaltyTicketId) {
              this.penaltyTicketId = penaltyTicketId;
       }

       public Long getBusEntity_Id() {
              return busEntity_Id;
       }

       public void setBusEntity_Id(Long busEntity_Id) {
              this.busEntity_Id = busEntity_Id;
       }

       public Long getEmployeeEntity_Id() {
              return employeeEntity_Id;
       }

       public void setEmployeeEntity_Id(Long employeeEntity_Id) {
              this.employeeEntity_Id = employeeEntity_Id;
       }

       public LocalDateTime getPenaltyDay() {
              return penaltyDay;
       }

       public void setPenaltyDay(LocalDateTime penaltyDay) {
              this.penaltyDay = penaltyDay;
       }

       public String getDescription() {
              return description;
       }

       public void setDescription(String description) {
              this.description = description;
       }

       public Boolean getResponsibility() {
              return responsibility;
       }

       public void setResponsibility(Boolean responsibility) {
              this.responsibility = responsibility;
       }

       public float getPrice() {
              return price;
       }

       public void setPrice(float price) {
              this.price = price;
       }

       public Boolean getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(Boolean isDelete) {
              this.isDelete = isDelete;
       }

}
