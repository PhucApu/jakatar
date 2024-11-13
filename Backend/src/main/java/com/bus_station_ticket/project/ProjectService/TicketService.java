package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectConfig.ResponseBoolAndMess;
import com.bus_station_ticket.project.ProjectDTO.TicketDTO;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.TicketMapping;
import com.bus_station_ticket.project.ProjectRepository.FeedbackRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Service
public class TicketService implements SimpleServiceInf<TicketEntity, TicketDTO, Long> {

       

       @Autowired
       private TicketRepo repo;

       @Autowired
       private FeedbackRepo feedbackRepo;

       @Autowired
       private TicketMapping ticketMapping;

       // Lấy một đối tượng TicketEntity theo giá trị
       // Input: ticketId (Long)
       // Output: TicketEntity có giá trị ticketId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public TicketEntity getById(Long ticketId) {
              return this.repo.findByTicketId(ticketId).orElse(null);
       }

       // Mapping đối tượng TicketEntity --> TicketDTO
       // Input: ticketId (Long)
       // Output: TicketDTO có giá trị ticketId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public TicketDTO getById_toDTO(Long ticketId) {
              TicketEntity ticketEntity = this.repo.findByTicketId(ticketId).orElse(null);

              if (ticketEntity != null) {
                     return this.ticketMapping.toDTO(ticketEntity);
              }
              return null;
       }
       
       // Lấy tất cả các đối tượng TicketEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<TicketEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<TicketEntity> --> List<TicketDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<TicketDTO> getAll_toDTO() {
              List<TicketEntity> listTicketEntities = this.repo.findAll();

              List<TicketDTO> listTicketDTOs = new ArrayList<>();

              if (listTicketEntities.isEmpty() == false) {
                     for (TicketEntity e : listTicketEntities) {
                            listTicketDTOs.add(this.ticketMapping.toDTO(e));
                     }
                     return listTicketDTOs;
              }
              return listTicketDTOs;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess delete(Long id) {

              Optional<TicketEntity> optional = this.repo.findByTicketId(id);

              if(optional.isPresent()){
                     Boolean check = isForeignKeyViolationIfDelete(optional.get());

                     if(check){
                            this.repo.delete(optional.get());
                            return new ResponseBoolAndMess(true, MESS_DELETE_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_DELETE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save(TicketEntity entityObj) {
              
              Optional<TicketEntity> optional = this.repo.findByTicketId(entityObj.getTicketId());

              if(optional.isPresent() == false){
                     this.repo.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_SAVE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save_toDTO(TicketDTO dtoObj) {
              TicketEntity ticketEntity = this.ticketMapping.toEntity(dtoObj);
              
              return save(ticketEntity);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update(TicketEntity entityObj) {
              
              Optional<TicketEntity> optional = this.repo.findByTicketId(entityObj.getTicketId());

              if(optional.isPresent()){
                     this.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_UPDATE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_UPDATE_FAILURE + "," + MESS_OBJECT_NOT_EXIST);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update_toDTO(TicketDTO dtoObj) {

              TicketEntity ticketEntity = this.ticketMapping.toEntity(dtoObj);
              
              return update(ticketEntity);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess invisibleWithoutDelete(Long id) {
              // 
              Optional<TicketEntity> optional = this.repo.findByTicketId(id);

              if(optional.isPresent()){
                     Boolean check = isForeignKeyViolationIfHidden(optional.get());

                     if(check){
                            TicketEntity ticketEntity = optional.get();
                            ticketEntity.setIsDelete(true);
                            this.save(ticketEntity);
                            return new ResponseBoolAndMess(true, MESS_HIDDEN_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_HIDDEN_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional
       @Override
       public Boolean isForeignKeyViolationIfDelete(TicketEntity entityObj) {
              
              // Ticket foreign key Feedback
              List<FeedbackEntity> feedbackEntities = this.feedbackRepo.findByTicketEntity_Id(entityObj.getTicketId());

              if(feedbackEntities.isEmpty() == false){
                     return false;
              }

              return true;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyViolationIfHidden(TicketEntity entityObj) {
              
              // Ticket foreign key Feedback
              List<FeedbackEntity> feedbackEntities = this.feedbackRepo.findByTicketEntity_Id(entityObj.getTicketId());

              if(feedbackEntities.isEmpty() == false){
                     for(FeedbackEntity e : feedbackEntities){
                            if(e.getIsDelete() == false){
                                   return false;
                            }
                     }
                     return true;
              }

              return true;
       }

       

}
