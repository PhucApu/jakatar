package com.bus_station_ticket.project.ProjectRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.bus_station_ticket.project.ProjectEntity.AccountEnity;
import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;


@Repository
public interface AccountRepo extends JpaRepository<AccountEnity,String> {
       
       // Tìm kiếm theo thuộc tính
       public Optional<AccountEnity> findByUserName(String userName);
       
       public Optional<AccountEnity> findByPassWord(String passWord);
       
       public Optional<AccountEnity> findByEmail(String email);
       
       public Optional<AccountEnity> findByPhoneNumber(String phone);
       
       public List<AccountEnity> findByRole(String role);
       
       public List<AccountEnity> findByIsBlock(ChoiceEnum isBlock);
       
       public List<AccountEnity> findByIsDelete(ChoiceEnum isDelete);


       @Query(
              value = """
                     select * 
                     from account acc,feedback fb
                     where acc.username = fb.username and fb.feedback_id = :feedbackId
              """,
              nativeQuery = true

       )
       public Optional<AccountEnity> findByFeedbackEntity_Id(@Param("feedbackId") Long feedbackId);

       @Query(
              value = """
                     select * 
                     from account acc,ticket tk
                     where acc.username = tk.username_id and tk.ticket_id = :ticketId
              """,
              nativeQuery = true

       )
       public Optional<AccountEnity> findByListTicketEntity_Id (@Param("ticketId") Long ticketId);
}
