package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BusRoutesDTO {

       @NotNull(message = "Routes ID cannot be null")
       private Long routesId;

       @NotBlank(message = "Departure location cannot be blank")
       @Size(max = 100, message = "Departure location must be less than 100 characters")
       private String departureLocation;

       @NotBlank(message = "Destination location cannot be blank")
       @Size(max = 100, message = "Destination location must be less than 100 characters")
       private String destinationLocation;

       @Min(value = 1, message = "Distance must be at least 1 kilometer")
       private float distanceKilometer;

       @NotNull(message = "Departure time cannot be null")
       private LocalDateTime departureTime;

       @NotNull(message = "Arrival time cannot be null")
       private LocalDateTime arivalTime;

       @Min(value = 0, message = "Price must be greater than or equal to 0")
       private float price;

       @NotNull(message = "Delete status cannot be null")
       private Boolean isDelete;

       @NotNull(message = "Ticket entity IDs cannot be null")
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
