package com.bus_station_ticket.project.ProjectRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.bus_station_ticket.project.ProjectEntity.AccountEnity;
import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

@Repository
public interface AccountRepo extends JpaRepository<AccountEnity,String> {
       
       // Tìm kiếm theo thuộc tính
       public Optional<AccountEnity> findByUserName(String userName);
       public Optional<AccountEnity> findByPassWord(String passWord);
       public Optional<AccountEnity> findByEmail(String email);
       public Optional<AccountEnity> findByPhoneNumber(String phone);
       public Optional<List<AccountEnity>> findByRole(String role);
       public Optional<List<AccountEnity>> findByIsBlock(ChoiceEnum isBlock);
       public Optional<List<AccountEnity>> findByIsDelete(ChoiceEnum isDelete);

       public Optional<List<FeedbackEntity>> findByAccountEntity(AccountEnity accountEnity);
       public Optional<List<TicketEntity>> findByTicketEntity(TicketEntity ticketEntity);
       
}
