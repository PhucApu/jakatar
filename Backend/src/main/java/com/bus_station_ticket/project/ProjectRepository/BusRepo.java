package com.bus_station_ticket.project.ProjectRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;

@Repository
public interface BusRepo extends JpaRepository<BusEntity,Long> {
       
       // Truy suất theo thuộc tính
       public Optional<BusEntity> findByBusId (Long busId);
       public Optional<BusEntity> findByBusNumber (String busNumber);
       public List<BusEntity> findByCapacity (int capacity);
       public List<BusEntity> findByBrand (String brand);
       public List<BusEntity> findByIsDelete (ChoiceEnum isDelete);

       @Query(
              value = """
                     select *
                     from bus b, employee e, bus_employee be
                     where e.driver_id = be.driver_id and be.bus_id = b.bus_id and e.driver_id = :driverId
              """,
              nativeQuery = true

       )
       public List<BusEntity> findByEmployeeEntity_Id (@Param("driverId") Long driverId);

       @Query(
              value = """
                     select *
                     from bus b, ticket tk
                     where b.bus_id = tk.bus_id and tk.ticket_id = :ticketId
              """,
              nativeQuery = true 
       )
       public Optional<BusEntity> findByTicketEntity_Id (@Param("ticketId") Long ticketId);

}
