package com.bus_station_ticket.project.ProjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "bus", indexes = @Index(columnList = "bus_id"))
public class BusEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "bus_id")
       private Long busId;

       @Column(name = "bus_number", nullable = false, columnDefinition = "VARCHAR(30)")
       private String busNumber;

       @Column(name = "capacity", nullable = false)
       private int capacity;

       @Column(name = "brand", nullable = false, columnDefinition = "VARCHAR(30)")
       private String brand;

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;

       @ManyToMany(mappedBy = "listBusEntity", fetch = FetchType.LAZY)
       private List<EmployeeEntity> listEmployeeEntities;


       public BusEntity() {
       }

       public BusEntity(String busNumber, int capacity, String brand, ChoiceEnum isDelete,
                     List<EmployeeEntity> listEmployeeEntities) {
              this.busNumber = busNumber;
              this.capacity = capacity;
              this.brand = brand;
              this.isDelete = isDelete;
              this.listEmployeeEntities = listEmployeeEntities;
       }

       public Long getBusId() {
              return busId;
       }

       public void setBusId(Long busId) {
              this.busId = busId;
       }

       public String getBusNumber() {
              return busNumber;
       }

       public void setBusNumber(String busNumber) {
              this.busNumber = busNumber;
       }

       public int getCapacity() {
              return capacity;
       }

       public void setCapacity(int capacity) {
              this.capacity = capacity;
       }

       public String getBrand() {
              return brand;
       }

       public void setBrand(String brand) {
              this.brand = brand;
       }

       public ChoiceEnum getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(ChoiceEnum isDelete) {
              this.isDelete = isDelete;
       }

       public List<EmployeeEntity> getListEmployeeEntities() {
              return listEmployeeEntities;
       }

       public void setListEmployeeEntities(List<EmployeeEntity> listEmployeeEntities) {
              this.listEmployeeEntities = listEmployeeEntities;
       }

       @Override
       public String toString() {
              return "BusEntity [busId=" + busId + ", busNumber=" + busNumber + ", capacity=" + capacity + ", brand="
                            + brand + ", isDelete=" + isDelete + ", listEmployeeEntities=" + listEmployeeEntities + "]";
       }
       
}
