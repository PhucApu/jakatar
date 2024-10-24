package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.FeedbackDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEntity;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Component
public class FeedbackMapping implements MappingInterface <FeedbackEntity,FeedbackDTO> {

       @Autowired
       private AccountRepo accountRepo;

       @Autowired
       private TicketRepo ticketRepo;

       @Override
       public FeedbackDTO toDTO(FeedbackEntity entity) {
              
              FeedbackDTO feedbackDTO = new FeedbackDTO();
              // Mapping các thuộc tính đơn giản
              feedbackDTO.setFeedbackId(entity.getFeedbackId());
              feedbackDTO.setAccountEnity_userName(entity.getAccountEnitty().getUserName());
              feedbackDTO.setTicketEntity_Id(entity.getTicketEntity().getTicketId());
              feedbackDTO.setContent(entity.getContent());
              feedbackDTO.setRating(entity.getRating());
              feedbackDTO.setDateComment(entity.getDateComment());
              feedbackDTO.setIsDelete(entity.getIsDelete());

              return feedbackDTO;
       }

       @Override
       public FeedbackEntity toEntity(FeedbackDTO dto) {
              
              FeedbackEntity feedbackEntity = new FeedbackEntity();
              // Mapping các thuộc tính cơ bản
              feedbackEntity.setFeedbackId(dto.getFeedbackId());
              feedbackEntity.setContent(dto.getContent());
              feedbackEntity.setRating(dto.getRating());
              feedbackEntity.setDateComment(dto.getDateComment());
              feedbackEntity.setIsDelete(dto.getIsDelete());

              // Mapping các thuộc tính phức tạp
              AccountEntity accountEntity = this.accountRepo.findByUserName(dto.getAccountEnity_userName()).orElse(null);

              feedbackEntity.setAccountEnitty(accountEntity);
              
              
              TicketEntity ticketEntity = this.ticketRepo.findByTicketId(dto.getTicketEntity_Id()).orElse(null);

              feedbackEntity.setTicketEntity(ticketEntity);


              return feedbackEntity;
       }

       
}
