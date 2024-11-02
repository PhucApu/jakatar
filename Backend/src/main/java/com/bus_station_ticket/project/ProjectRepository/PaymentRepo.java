package com.bus_station_ticket.project.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.bus_station_ticket.project.ProjectEntity.PaymentEntity;


public interface PaymentRepo extends JpaRepository<PaymentEntity,Long> {
       
       // truy suất theo thuộc tính
       public Optional<PaymentEntity> findByPaymentId (Long paymentId);
       
       public List<PaymentEntity> findByPaymentTime (LocalDateTime paymentTime);
       
       public List<PaymentEntity> findByPaymentMethod (String paymentMethod);
       
       public List<PaymentEntity> findByIsDelete (Boolean isDelete);

       @Query(
              value = """
                     select *
                     from payment p, ticket tk
                     where p.payment_id = tk.payment_id and tk.ticket_id = :ticketId
              """,
              nativeQuery = true

       )
       public Optional<PaymentEntity> findByTicketEntity_Id (@Param("ticketId") Long ticketId);
       
}
