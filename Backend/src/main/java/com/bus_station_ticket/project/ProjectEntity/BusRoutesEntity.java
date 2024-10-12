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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bus_routes", indexes = @Index(columnList = "routes_id"))
public class BusRoutesEntity {
       
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "routes_id", columnDefinition = "VARCHAR(20)")
       private String routesId;

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

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;

       @OneToMany(mappedBy = "busRoutesEntity", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;

       
}
