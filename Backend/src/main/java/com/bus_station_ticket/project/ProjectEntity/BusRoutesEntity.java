package com.bus_station_ticket.project.ProjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bus_routes", indexes = @Index(columnList = "routes_id"))
public class BusRoutesEntity {
       
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "routes_id")
       private Long routesId;

       @Column(name = "departure_location", columnDefinition = "VARCHAR(50)")
       private String departureLocation;

       @Column(name = "destination_location", columnDefinition = "VARCHAR(50)")
       private String destinationLocation;

       @Column(name = "distance_location", columnDefinition = "FLOAT", length = 5)
       private float distanceKilometer;

       @Column(name = "departure_time", columnDefinition = "DATETIME")
       private LocalDateTime departureTime;

       @Column(name = "arival_time", columnDefinition = "DATETIME")
       private LocalDateTime arivalTime;

       @Column(name = "price", columnDefinition = "FLOAT", length = 5)
       private float price;

       @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean isDelete;

       @OneToMany(mappedBy = "busRoutesEntity", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;


       public BusRoutesEntity() {
       }

       public BusRoutesEntity(String departureLocation, String destinationLocation, float distanceKilometer,
                     LocalDateTime departureTime, LocalDateTime arivalTime, float price, Boolean isDelete,
                     List<TicketEntity> listTicketEntities) {
              this.departureLocation = departureLocation;
              this.destinationLocation = destinationLocation;
              this.distanceKilometer = distanceKilometer;
              this.departureTime = departureTime;
              this.arivalTime = arivalTime;
              this.price = price;
              this.isDelete = isDelete;
              this.listTicketEntities = listTicketEntities;
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

       public List<TicketEntity> getListTicketEntities() {
              return listTicketEntities;
       }

       public void setListTicketEntities(List<TicketEntity> listTicketEntities) {
              this.listTicketEntities = listTicketEntities;
       }

       
}
