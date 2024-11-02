package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;
import java.util.List;

public class BusRoutesDTO {

       private Long routesId;
       private String departureLocation;
       private String destinationLocation;
       private float distanceKilometer;
       private LocalDateTime departureTime;
       private LocalDateTime arivalTime;
       private float price;
       private Boolean isDelete;
       private List<Long> listTicketEntities_Id;

       public BusRoutesDTO() {
       }

       public BusRoutesDTO(Long routesId, String departureLocation, String destinationLocation, float distanceKilometer,
                     LocalDateTime departureTime, LocalDateTime arivalTime, float price, Boolean isDelete,
                     List<Long> listTicketEntities_Id) {
              this.routesId = routesId;
              this.departureLocation = departureLocation;
              this.destinationLocation = destinationLocation;
              this.distanceKilometer = distanceKilometer;
              this.departureTime = departureTime;
              this.arivalTime = arivalTime;
              this.price = price;
              this.isDelete = isDelete;
              this.listTicketEntities_Id = listTicketEntities_Id;
       }

       public Long getRoutesId() {
              return routesId;
       }

       public void setRoutesId(Long routesId) {
              this.routesId = routesId;
       }

       public String getDepartureLocation() {
              return departureLocation;
       }

       public void setDepartureLocation(String departureLocation) {
              this.departureLocation = departureLocation;
       }

       public String getDestinationLocation() {
              return destinationLocation;
       }

       public void setDestinationLocation(String destinationLocation) {
              this.destinationLocation = destinationLocation;
       }

       public float getDistanceKilometer() {
              return distanceKilometer;
       }

       public void setDistanceKilometer(float distanceKilometer) {
              this.distanceKilometer = distanceKilometer;
       }

       public LocalDateTime getDepartureTime() {
              return departureTime;
       }

       public void setDepartureTime(LocalDateTime departureTime) {
              this.departureTime = departureTime;
       }

       public LocalDateTime getArivalTime() {
              return arivalTime;
       }

       public void setArivalTime(LocalDateTime arivalTime) {
              this.arivalTime = arivalTime;
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

       public List<Long> getListTicketEntities_Id() {
              return listTicketEntities_Id;
       }

       public void setListTicketEntities_Id(List<Long> listTicketEntities_Id) {
              this.listTicketEntities_Id = listTicketEntities_Id;
       }

}
