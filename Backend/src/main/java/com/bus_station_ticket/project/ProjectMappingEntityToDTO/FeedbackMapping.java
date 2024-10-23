package com.bus_station_ticket.project.ProjectMappingEntityToDTO;

import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.FeedbackDTO;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;

@Component
public class FeedbackMapping implements MappingInterface <FeedbackEntity,FeedbackDTO> {

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
}
