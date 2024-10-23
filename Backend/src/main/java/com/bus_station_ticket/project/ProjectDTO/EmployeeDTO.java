package com.bus_station_ticket.project.ProjectDTO;

import java.util.List;



public class EmployeeDTO {

       private Long driverId;
       private Boolean isDriver;
       private String driverName;
       private String licenseNumber;
       private String phoneNumber;
       private Boolean isDelete;
       private List<Long> listBusEntities_Id;

       public EmployeeDTO() {
       }

       public EmployeeDTO(Long driverId, Boolean isDriver, String driverName, String licenseNumber, String phoneNumber,
                     Boolean isDelete, List<Long> listBusEntities_Id) {
              this.driverId = driverId;
              this.isDriver = isDriver;
              this.driverName = driverName;
              this.licenseNumber = licenseNumber;
              this.phoneNumber = phoneNumber;
              this.isDelete = isDelete;
              this.listBusEntities_Id = listBusEntities_Id;
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

       public List<Long> getListBusEntities_Id() {
              return listBusEntities_Id;
       }

       public void setListBusEntities_Id(List<Long> listBusEntities_Id) {
              this.listBusEntities_Id = listBusEntities_Id;
       }

}
