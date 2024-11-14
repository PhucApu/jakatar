package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PenaltyTicketDTO {

       @NotNull(message = "penaltyTicketId cannot be blank")
       private Long penaltyTicketId;

       @NotNull(message = "Bus ID cannot be null")
       private Long busEntity_Id;

       @NotNull(message = "Employee ID cannot be null")
       private Long employeeEntity_Id;

       @NotNull(message = "Penalty day cannot be null")
       private LocalDateTime penaltyDay;

       @NotBlank(message = "Description cannot be blank")
       @Size(max = 500, message = "Description cannot exceed 500 characters")
       private String description;

       @NotNull(message = "Responsibility status cannot be null")
       private Boolean responsibility;

       @Min(value = 0, message = "Price must be greater than or equal to 0")
       private float price;

       @NotNull(message = "Delete status cannot be null")
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
