package com.bus_station_ticket.project.ProjectRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.bus_station_ticket.project.ProjectEntity.AccountEntity;


@Repository
public interface AccountRepo extends JpaRepository<AccountEntity,String> {
       
       // Tìm kiếm theo thuộc tính
       public Optional<AccountEntity> findByUserName(String userName);
       
       public Optional<AccountEntity> findByPassWord(String passWord);
       
       public Optional<AccountEntity> findByEmail(String email);
       
       public Optional<AccountEntity> findByPhoneNumber(String phone);
       
       public List<AccountEntity> findByRole(String role);
       
       public List<AccountEntity> findByIsBlock(Boolean isBlock);
       
       public List<AccountEntity> findByIsDelete(Boolean isDelete);


       @Query(
              value = """
                     select * 
                     from account acc,feedback fb
                     where acc.username = fb.username and fb.feedback_id = :feedbackId
              """,
              nativeQuery = true

       )
       public Optional<AccountEntity> findByFeedbackEntity_Id(@Param("feedbackId") Long feedbackId);

       @Query(
              value = """
                     select * 
                     from account acc,ticket tk
                     where acc.username = tk.username_id and tk.ticket_id = :ticketId
              """,
              nativeQuery = true

       )
       public Optional<AccountEntity> findByListTicketEntity_Id (@Param("ticketId") Long ticketId);
}
