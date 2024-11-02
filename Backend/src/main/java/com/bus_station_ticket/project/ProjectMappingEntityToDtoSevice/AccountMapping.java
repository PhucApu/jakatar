package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEntity;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectRepository.FeedbackRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Component
public class AccountMapping implements MappingInterface<AccountEntity, AccountDTO> {

       @Autowired
       private FeedbackRepo feedbackRepo;

       @Autowired
       private TicketRepo ticketRepo;

       @Override
       public AccountDTO toDTO(AccountEntity entity) {

              AccountDTO accountDTO = new AccountDTO();

              // mapping các thuộc tính cơ bản
              accountDTO.setUserName(entity.getUserName());
              accountDTO.setPassWord(entity.getPassWord());
              accountDTO.setEmail(entity.getEmail());
              accountDTO.setPhoneNumber(entity.getPhoneNumber());
              accountDTO.setRole(entity.getRole());
              accountDTO.setIsDelete(entity.getIsDelete());

              // mapping cac thuoc tinh list
              List<Long> listFeedbackEntities_Id = new ArrayList<>();
              List<Long> listTicketEntities_Id = new ArrayList<>();

              if(entity.getListFeedbackEntities() != null){
                     for (FeedbackEntity e : entity.getListFeedbackEntities()) {
                            listFeedbackEntities_Id.add(e.getFeedbackId());
                     }
              }
              if(entity.getListTicketEntities() != null){
                     for (TicketEntity e : entity.getListTicketEntities()) {
                            listTicketEntities_Id.add(e.getTicketId());
                     }
              }

              accountDTO.setListFeedbackEntities_Id(listFeedbackEntities_Id);
              accountDTO.setListTicketEntities_Id(listTicketEntities_Id);

              return accountDTO;
       }

       @Override
       public AccountEntity toEntity(AccountDTO dto) {

              AccountEntity accountEntity = new AccountEntity();

              // Mapping các thuộc tính cơ bản
              accountEntity.setUserName(dto.getUserName());
              accountEntity.setPassWord(dto.getPassWord());
              accountEntity.setEmail(dto.getEmail());
              accountEntity.setPhoneNumber(dto.getPhoneNumber());
              accountEntity.setRole(dto.getRole());
              accountEntity.setIsBlock(dto.getIsBlock());
              accountEntity.setIsDelete(dto.getIsDelete());

              // Mapping các thuộc tinh List

              List<FeedbackEntity> listFeedbackEntities = new ArrayList<>();

              if(dto.getListFeedbackEntities_Id() != null){
                     for (Long value : dto.getListFeedbackEntities_Id()) {
                            FeedbackEntity feedbackEntity = this.feedbackRepo.findByFeedbackId(value).orElse(null);
       
                            listFeedbackEntities.add(feedbackEntity);
                     }
              }
              accountEntity.setListFeedbackEntities(listFeedbackEntities);

              List<TicketEntity> listTicketEntities = new ArrayList<>();
              if(dto.getListTicketEntities_Id() != null){
                     for (Long value : dto.getListTicketEntities_Id()){
                            TicketEntity ticketEntity = this.ticketRepo.findByTicketId(value).orElse(null);
       
                            listTicketEntities.add(ticketEntity);
                     }
              }
              accountEntity.setListTicketEntities(listTicketEntities);

              return accountEntity;
       }

}
