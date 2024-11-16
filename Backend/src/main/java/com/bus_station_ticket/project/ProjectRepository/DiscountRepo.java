package com.bus_station_ticket.project.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bus_station_ticket.project.ProjectEntity.DiscountEntity;


public interface DiscountRepo extends JpaRepository<DiscountEntity,Long>{
       
       // Truy suất theo thuộc tính
       public Optional<DiscountEntity> findByDiscountId (Long discountId);
       
       public List<DiscountEntity> findByDiscountPercentage(float discountPercentage);
       
       public List<DiscountEntity> findByValidFrom (LocalDateTime validFrom);
       
       public List<DiscountEntity> findByValidUntil (LocalDateTime validUntil);
       
       public List<DiscountEntity> findByAmount (float amount);
       
       public List<DiscountEntity> findByIsDelete (Boolean isDelete);


       @Query(
              value = """
                     select dc.discount_id, dc.discount_percentage, dc.valid_from, dc.valid_until, dc.amount, dc.is_delete
                     from discount dc, ticket tk
                     where dc.discount_id = tk.discount_id and tk.ticket_id = :ticketId
              """,
              nativeQuery = true

       )
       public Optional<DiscountEntity> findByTicketEntity_Id (@Param("ticketId") Long ticketId);

       
}
