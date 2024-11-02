package com.bus_station_ticket.project.ProjectEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "penalty_ticket", indexes = @Index(columnList = "penalty_ticket_id"))
public class PenaltyTicketEntity {
       
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "penalty_ticket_id")
       private Long penaltyTicketId;

       @ManyToOne
       @JoinColumn(name = "bus_id", referencedColumnName = "bus_id", nullable = false, insertable = false, updatable = true)
       private BusEntity busEntity;

       @ManyToOne
       @JoinColumn(name = "driver_id", referencedColumnName = "driver_id" , nullable = true, insertable = false, updatable = true )
       private EmployeeEntity employeeEntity;

       @Column(name = "penalty_day", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime penaltyDay;

       @Column(name = "description", nullable =  false, columnDefinition = "TEXT")
       private String description;

       @Column(name = "responsibility" , nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean responsibility;
       
       @Column(name = "price", nullable = false, columnDefinition = "FLOAT", length = 5 )
       private float price;

       @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean isDelete;

       public PenaltyTicketEntity() {
       }

       public PenaltyTicketEntity(BusEntity busEntity, EmployeeEntity employeeEntity, LocalDateTime penaltyDay,
                     String description, Boolean responsibility, float price, Boolean isDelete) {
              this.busEntity = busEntity;
              this.employeeEntity = employeeEntity;
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

       public BusEntity getBusEntity() {
              return busEntity;
       }

       public void setBusEntity(BusEntity busEntity) {
              this.busEntity = busEntity;
       }

       public EmployeeEntity getEmployeeEntity() {
              return employeeEntity;
       }

       public void setEmployeeEntity(EmployeeEntity employeeEntity) {
              this.employeeEntity = employeeEntity;
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
