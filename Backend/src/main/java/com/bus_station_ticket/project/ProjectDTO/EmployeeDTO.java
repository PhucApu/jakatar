package com.bus_station_ticket.project.ProjectDTO;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {

       @NotNull(message = "Driver ID cannot be null")
       private Long driverId;

       @NotNull(message = "Driver status cannot be null")
       private Boolean isDriver;

       @NotBlank(message = "Driver name cannot be blank")
       @Size(max = 100, message = "Driver name must not exceed 100 characters")
       private String driverName;

       @NotBlank(message = "License number cannot be blank")
       @Pattern(regexp = "^[0-9]{12}$", message = "License number must be 12 numbers")
       private String licenseNumber;

       @NotBlank(message = "Phone number cannot be blank")
       @Pattern(regexp = "^(03|05|07|08|09)[0-9]{8}$", message = "Phone number is not valid")
       private String phoneNumber;

       @NotNull(message = "Delete status cannot be null")
       private Boolean isDelete;

       @NotNull(message = "Bus entity IDs cannot be null")
       private List<Long> listBusEntities_Id;

       @NotNull(message = "Penalty ticket entity IDs cannot be null")
       private List<Long> listPenaltyTicketEntities_Id;

       public EmployeeDTO() {
       }

       public EmployeeDTO(Boolean isDriver, String driverName, String licenseNumber, String phoneNumber,
                     Boolean isDelete, List<Long> listBusEntities_Id, List<Long> listPenaltyTicketEntities_Id) {
              this.isDriver = isDriver;
              this.driverName = driverName;
              this.licenseNumber = licenseNumber;
              this.phoneNumber = phoneNumber;
              this.isDelete = isDelete;
              this.listBusEntities_Id = listBusEntities_Id;
              this.listPenaltyTicketEntities_Id = listPenaltyTicketEntities_Id;
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

       public List<Long> getListPenaltyTicketEntities_Id() {
              return listPenaltyTicketEntities_Id;
       }

       public void setListPenaltyTicketEntities_Id(List<Long> listPenaltyTicketEntities_Id) {
              this.listPenaltyTicketEntities_Id = listPenaltyTicketEntities_Id;
       }

}
