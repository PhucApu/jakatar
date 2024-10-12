package com.bus_station_ticket.project.ProjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "employee", indexes = @Index(columnList = "driver_id"))
public class EmployeeEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "driver_id")
       private Long driverId;

       @Column(name = "is_driver", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDriver;

       @Column(name = "driver_name", nullable = false, columnDefinition = "VARCHAR(50)")
       private String driverName;

       @Column(name = "license_number", nullable = false, columnDefinition = "VARCHAR(50)")
       private String licenseNumber;

       @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(11)")
       private String phoneNumber;

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;

       @ManyToMany
       @JoinTable(
              name = "bus_employee", 
              joinColumns = @JoinColumn(name = "driver_id"), 
              inverseJoinColumns = @JoinColumn(name = "bus_id")
       )
       private List<BusEntity> listBusEntity;



       public EmployeeEntity() {
       }

       public EmployeeEntity(ChoiceEnum isDriver, String driverName, String licenseNumber, String phoneNumber,
                     ChoiceEnum isDelete, List<BusEntity> listBusEntity) {
              this.isDriver = isDriver;
              this.driverName = driverName;
              this.licenseNumber = licenseNumber;
              this.phoneNumber = phoneNumber;
              this.isDelete = isDelete;
              this.listBusEntity = listBusEntity;
       }

       public Long getDriverId() {
              return driverId;
       }

       public void setDriverId(Long driverId) {
              this.driverId = driverId;
       }

       public ChoiceEnum getIsDriver() {
              return isDriver;
       }

       public void setIsDriver(ChoiceEnum isDriver) {
              this.isDriver = isDriver;
       }

       public String getDriverName() {
              return driverName;
       }

       public void setDriverName(String driverName) {
              this.driverName = driverName;
       }

       public String getLicenseNumber() {
              return licenseNumber;
       }

       public void setLicenseNumber(String licenseNumber) {
              this.licenseNumber = licenseNumber;
       }

       public String getPhoneNumber() {
              return phoneNumber;
       }

       public void setPhoneNumber(String phoneNumber) {
              this.phoneNumber = phoneNumber;
       }

       public ChoiceEnum getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(ChoiceEnum isDelete) {
              this.isDelete = isDelete;
       }

       public List<BusEntity> getListBusEntity() {
              return listBusEntity;
       }

       public void setListBusEntity(List<BusEntity> listBusEntity) {
              this.listBusEntity = listBusEntity;
       }

       @Override
       public String toString() {
              return "EmployeeEntity [driverId=" + driverId + ", isDriver=" + isDriver + ", driverName=" + driverName
                            + ", licenseNumber=" + licenseNumber + ", phoneNumber=" + phoneNumber + ", isDelete="
                            + isDelete + ", listBusEntity=" + listBusEntity + "]";
       }

}
