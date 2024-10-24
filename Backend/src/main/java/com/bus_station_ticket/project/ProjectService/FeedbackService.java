package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.FeedbackDTO;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.FeedbackMapping;
import com.bus_station_ticket.project.ProjectRepository.FeedbackRepo;

@Service
public class FeedbackService {

       @Autowired
       private FeedbackRepo repo;

       @Autowired
       private FeedbackMapping feedbackMapping;

       // Lấy một đối tượng FeedbackEntity theo giá trị
       // Input: feedbackId (Long)
       // Output: FeedbackEntity có giá trị feedbackId tương ứng

       public FeedbackEntity getByFeedbackId(Long feedbackId) {
              return this.repo.findByFeedbackId(feedbackId).orElse(null);
       }

       // Mapping đối tượng FeedbackEntity --> FeedbackDTO
       // Input: feedbackId (Long)
       // Output: FeedbackDTO có giá trị feedbackId tương ứng

       public FeedbackDTO getByFeedbackId_toDTO(Long feedbackId) {
              FeedbackEntity feedbackEntity = this.repo.findByFeedbackId(feedbackId).orElse(null);

              if (feedbackEntity != null) {
                     return this.feedbackMapping.toDTO(feedbackEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng FeedbackEntity
       // Input:
       // Output: List

       public List<FeedbackEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<FeedbackEntity> --> List<FeedbackDTO>

       public List<FeedbackDTO> getAll_toDTO() {

              List<FeedbackEntity> listFeedbackEntities = this.repo.findAll();

              List<FeedbackDTO> listFeedbackDTOs = new ArrayList<>();

              if (listFeedbackEntities.isEmpty() == false) {
                     for (FeedbackEntity e : listFeedbackEntities) {
                            listFeedbackDTOs.add(this.feedbackMapping.toDTO(e));
                     }
                     return listFeedbackDTOs;
              }
              return listFeedbackDTOs;
       }
}
