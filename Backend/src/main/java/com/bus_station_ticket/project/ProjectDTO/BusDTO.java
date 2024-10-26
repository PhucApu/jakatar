package com.bus_station_ticket.project.ProjectDTO;

import java.util.List;


public class BusDTO {
       private Long busId;
       private String busNumber;
       private int capacity;
       private String brand;
       private Boolean isDelete;
       private List<Long> listEmployeeEntities_Id;
       private List<Long> listTicketEntities_Id;
       private List<Long> listPenaltyTicketEntities_Id;
       
       
       public BusDTO() {
       }


       public BusDTO(String busNumber, int capacity, String brand, Boolean isDelete, List<Long> listEmployeeEntities_Id,
                     List<Long> listTicketEntities_Id, List<Long> listPenaltyTicketEntities_Id) {
              this.busNumber = busNumber;
              this.capacity = capacity;
              this.brand = brand;
              this.isDelete = isDelete;
              this.listEmployeeEntities_Id = listEmployeeEntities_Id;
              this.listTicketEntities_Id = listTicketEntities_Id;
              this.listPenaltyTicketEntities_Id = listPenaltyTicketEntities_Id;
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
       public Boolean getIsDelete() {
              return isDelete;
       }
       public void setIsDelete(Boolean isDelete) {
              this.isDelete = isDelete;
       }
       public List<Long> getListEmployeeEntities_Id() {
              return listEmployeeEntities_Id;
       }
       public void setListEmployeeEntities_Id(List<Long> listEmployeeEntities_Id) {
              this.listEmployeeEntities_Id = listEmployeeEntities_Id;
       }
       public List<Long> getListTicketEntities_Id() {
              return listTicketEntities_Id;
       }
       public void setListTicketEntities_Id(List<Long> listTicketEntities_Id) {
              this.listTicketEntities_Id = listTicketEntities_Id;
       }

       public List<Long> getListPenaltyTicketEntities_Id() {
              return listPenaltyTicketEntities_Id;
       }

       public void setListPenaltyTicketEntities_Id(List<Long> listPenaltyTicketEntities_Id) {
              this.listPenaltyTicketEntities_Id = listPenaltyTicketEntities_Id;
       }

       
}
