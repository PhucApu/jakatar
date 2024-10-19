package com.bus_station_ticket.project.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;

@Repository
public interface BusRoutesRepo extends JpaRepository<BusRoutesEntity,Long> {
       
       // Truy suất theo thuộc tính
       public Optional<BusRoutesEntity> findByRoutesId (Long routesId);
       
       public Optional<BusRoutesEntity> findByDepartureLocation (String departureLocation);
       
       public Optional<BusRoutesEntity> findByDestinationLocation (String destinationLocation);
       
       public List<BusRoutesEntity> findByDistanceKilometer (float distanceKilometer);
       
       public List<BusRoutesEntity> findByDepartureTime (LocalDateTime departureTime);
       
       public List<BusRoutesEntity> findByArivalTime (LocalDateTime arivalTime);
       
       public List<BusRoutesEntity> findByPrice (float price);
       
       public List<BusRoutesEntity> findByIsDelete (ChoiceEnum isDelete);


       @Query(
              value = """
                     select *
                     from bus_routes br, ticket tk
                     where br.routes_id = tk.routes_id and tk.ticket_id = :ticketId
              """,
              nativeQuery = true
       )
       public Optional<BusRoutesEntity> findByTicketEntity_Id(@Param("ticketId") Long ticketId);

}
