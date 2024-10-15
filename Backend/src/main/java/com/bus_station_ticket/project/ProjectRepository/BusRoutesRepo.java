package com.bus_station_ticket.project.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

@Repository
public interface BusRoutesRepo extends JpaRepository<BusRoutesEntity,Long> {
       
       // Truy suất theo thuộc tính
       public Optional<BusRoutesEntity> findByDepartureLocation (String departureLocation);
       public Optional<BusRoutesEntity> findByDestinationLocation (String destinationLocation);
       public Optional<List<BusRoutesEntity>> findByDistanceKilometer (String distanceKilometer);
       public Optional<List<BusRoutesEntity>> findByDepartureTime (LocalDateTime departureTime);
       public Optional<List<BusRoutesEntity>> findByArivalTime (LocalDateTime arivalTime);
       public Optional<List<BusRoutesEntity>> findByPrice (float price);
       public Optional<List<BusRoutesEntity>> findByIsDelete (ChoiceEnum isDelete);
       public Optional<List<TicketEntity>> findByBusRoutesEntity(BusRoutesEntity busRoutesEntity);

}
