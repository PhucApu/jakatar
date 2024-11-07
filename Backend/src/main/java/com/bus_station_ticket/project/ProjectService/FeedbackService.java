package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectDTO.FeedbackDTO;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.FeedbackMapping;
import com.bus_station_ticket.project.ProjectRepository.FeedbackRepo;

@Service
public class FeedbackService implements SimpleServiceInf<FeedbackEntity,FeedbackDTO,Long> {

       @Autowired
       private FeedbackRepo repo;

       @Autowired
       private FeedbackMapping feedbackMapping;

       // Lấy một đối tượng FeedbackEntity theo giá trị
       // Input: feedbackId (Long)
       // Output: FeedbackEntity có giá trị feedbackId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public FeedbackEntity getById(Long feedbackId) {
              return this.repo.findByFeedbackId(feedbackId).orElse(null);
       }

       // Mapping đối tượng FeedbackEntity --> FeedbackDTO
       // Input: feedbackId (Long)
       // Output: FeedbackDTO có giá trị feedbackId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public FeedbackDTO getById_toDTO(Long feedbackId) {
              FeedbackEntity feedbackEntity = this.repo.findByFeedbackId(feedbackId).orElse(null);

              if (feedbackEntity != null) {
                     return this.feedbackMapping.toDTO(feedbackEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng FeedbackEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<FeedbackEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<FeedbackEntity> --> List<FeedbackDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
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

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean delete(Long id) {
              
              // kiem tra
              Optional<FeedbackEntity> optional = this.repo.findByFeedbackId(id);

              // neu co kq
              if(optional.isPresent()){
                     // xoa
                     this.repo.delete(optional.get());
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save(FeedbackEntity entityObj) {
              
              Optional<FeedbackEntity> optional = this.repo.findByFeedbackId(entityObj.getFeedbackId());

              // neu khong co ket qua
              if(optional.isPresent() == false){
                     // them
                     this.repo.save(entityObj);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save_toDTO(FeedbackDTO dtoObj) {
              
              // kiem tra ket qua
              Optional<FeedbackEntity> optional = this.repo.findByFeedbackId(dtoObj.getFeedbackId());

              // neu kq khong co
              if(optional.isPresent() == false){

                     // mapping
                     FeedbackEntity feedbackEntity = this.feedbackMapping.toEntity(dtoObj);
                     
                     // them
                     this.repo.save(feedbackEntity);
                     return true; 
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update(FeedbackEntity entityObj) {
       
              // kiem tra ket qua
              Optional<FeedbackEntity> optional = this.repo.findByFeedbackId(entityObj.getFeedbackId());

              // neu co ton tai
              if(optional.isPresent()){
                     //sua
                     this.repo.save(optional.get());
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update_toDTO(FeedbackDTO dtoObj) {
       
              // kiem tra ket qua
              Optional<FeedbackEntity> optional = this.repo.findByFeedbackId(dtoObj.getFeedbackId());

              // neu co kq
              if(optional.isPresent()){
                     // mapping
                     FeedbackEntity feedbackEntity = this.feedbackMapping.toEntity(dtoObj);

                     // sua
                     this.repo.save(feedbackEntity);
                     return true;
              }

              return false;
       }

       
}
