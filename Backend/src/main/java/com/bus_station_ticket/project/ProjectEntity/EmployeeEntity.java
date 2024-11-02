package com.bus_station_ticket.project.ProjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "employee", indexes = @Index(columnList = "driver_id"))
public class EmployeeEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "driver_id")
       private Long driverId;

       @Column(name = "is_driver", nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
       private Boolean isDriver;

       @Column(name = "driver_name", nullable = false, columnDefinition = "VARCHAR(50)")
       private String driverName;

       @Column(name = "license_number", nullable = false, columnDefinition = "VARCHAR(50)")
       private String licenseNumber;

       @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(11)")
       private String phoneNumber;

       @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean isDelete;

       @ManyToMany
       @JoinTable(
              name = "bus_employee", 
              joinColumns = @JoinColumn(name = "driver_id"), 
              inverseJoinColumns = @JoinColumn(name = "bus_id")
       )
       private List<BusEntity> listBusEntity;

       @OneToMany(mappedBy = "employeeEntity", fetch = FetchType.LAZY)
       private List<PenaltyTicketEntity> listPenaltyTicketEntities;



       public EmployeeEntity() {
       }

       

       public EmployeeEntity(Boolean isDriver, String driverName, String licenseNumber, String phoneNumber,
                     Boolean isDelete, List<BusEntity> listBusEntity,
                     List<PenaltyTicketEntity> listPenaltyTicketEntities) {
              this.isDriver = isDriver;
              this.driverName = driverName;
              this.licenseNumber = licenseNumber;
              this.phoneNumber = phoneNumber;
              this.isDelete = isDelete;
              this.listBusEntity = listBusEntity;
              this.listPenaltyTicketEntities = listPenaltyTicketEntities;
       }



       public Long getDriverId() {
              return driverId;
       }

       public void setDriverId(Long driverId) {
              this.driverId = driverId;
       }

       public Boolean getIsDriver() {
              return isDriver;
       }

       public void setIsDriver(Boolean isDriver) {
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

       public Boolean getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(Boolean isDelete) {
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

       public List<PenaltyTicketEntity> getListPenaltyTicketEntities() {
              return listPenaltyTicketEntities;
       }

       public void setListPenaltyTicketEntities(List<PenaltyTicketEntity> listPenaltyTicketEntities) {
              this.listPenaltyTicketEntities = listPenaltyTicketEntities;
       }

}
