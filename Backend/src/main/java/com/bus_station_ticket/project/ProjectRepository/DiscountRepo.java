package com.bus_station_ticket.project.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;
import com.bus_station_ticket.project.ProjectEntity.DiscountEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

public interface DiscountRepo extends JpaRepository<DiscountEntity,Long>{
       
       // Truy suất theo thuộc tính
       public Optional<List<DiscountEntity>> findByDiscountPercentage(float discountPercentage);
       public Optional<List<DiscountEntity>> findByValidFrom (LocalDateTime validFrom);
       public Optional<List<DiscountEntity>> findByValidUntil (LocalDateTime validUntil);
       public Optional<List<DiscountEntity>> findByAmount (float amount);
       public Optional<List<DiscountEntity>> findByIsDelete (ChoiceEnum isDelete);
       public Optional<List<TicketEntity>> findByDiscountEntity (DiscountEntity discountEntity);

       
}
