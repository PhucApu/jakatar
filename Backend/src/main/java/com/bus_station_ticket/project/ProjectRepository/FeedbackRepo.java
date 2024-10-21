package com.bus_station_ticket.project.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

public interface FeedbackRepo extends JpaRepository<FeedbackEntity,Long>{
       
       // Truy suất theo thuộc tính
       public Optional<FeedbackEntity> findByFeedbackId (Long feedbackId);
       
       public List<FeedbackEntity> findByTicketEntity (TicketEntity ticketEntity);
       
       public List<FeedbackEntity> findByRating (int rating);
       
       public List<FeedbackEntity> findByDateComment (LocalDateTime dateComment);
       
       public List<FeedbackEntity> findByIsDelete (Boolean isDelete);
          
       @Query(
              value = """
                     select *
                     from feedback fb, account acc
                     where fb.username = acc.username and fb.username = :username
              """,
              nativeQuery = true
       )
       public List<FeedbackEntity> findByAccountEntity_userName (@Param("username") String username);
       
       @Query(
              value = """
                     select *
                     from feedback fb, ticket tk
                     where tk.ticket_id = fb.ticket_id and fb.ticket_id = :ticketId
              """,
              nativeQuery = true
       )
       public List<FeedbackEntity> findByTicketEntity_Id (@Param("ticketId") Long ticketId);

}
