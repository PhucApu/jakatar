package com.bus_station_ticket.project.ProjectMappingEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEnity;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

@Component
public class AccountMapping implements MappingInterface<AccountEnity,AccountDTO> {
       

       @Override
       public AccountDTO toDTO(AccountEnity entity) {

              AccountDTO accountDTO = new AccountDTO();
              
              // mapping các thuộc tính cơ bản
              accountDTO.setUserName(entity.getUserName());
              accountDTO.setPassWord(entity.getPassWord());
              accountDTO.setEmail(entity.getEmail());
              accountDTO.setPhoneNumber(entity.getPhoneNumber());
              accountDTO.setRole(entity.getRole());

              // mapping cac thuoc tinh list 
              List<Long> listFeedbackEntities_Id = new ArrayList<>();
              List<Long> listTicketEntities_Id = new ArrayList<>();

              for (FeedbackEntity e : entity.getListFeedbackEntities()) {
                     listFeedbackEntities_Id.add(e.getFeedbackId());
              }

              for (TicketEntity e : entity.getListTicketEntities()) {
                     listTicketEntities_Id.add(e.getTicketId());
              }

              accountDTO.setListFeedbackEntities_Id(listFeedbackEntities_Id);
              accountDTO.setListTicketEntities_Id(listTicketEntities_Id);
       
              return accountDTO;
       }

       
       
       
}
