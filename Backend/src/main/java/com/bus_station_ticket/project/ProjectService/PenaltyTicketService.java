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
import com.bus_station_ticket.project.ProjectDTO.PenaltyTicketDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectEntity.PenaltyTicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.PenaltyTicketMapping;
import com.bus_station_ticket.project.ProjectRepository.PenaltyTicketRepo;

@Service
public class PenaltyTicketService implements SimpleServiceInf<PenaltyTicketEntity, PenaltyTicketDTO, Long> {

       @Autowired
       private PenaltyTicketRepo repo;

       @Autowired
       private PenaltyTicketMapping penaltyTicketMapping;

       // Lấy một đối tượng PenaltyTicketEntity theo giá trị
       // Input: penaltyTicketId (Long)
       // Output: PenaltyTicketEntity có giá trị penaltyTicketId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public PenaltyTicketEntity getById(Long penaltyTicketId) {
              return this.repo.findByPenaltyTicketId(penaltyTicketId).orElse(null);
       }

       // Mapping đối tượng PenaltyTicketEntity --> PenaltyTicketDTO
       // Input: penaltyTicketId (Long)
       // Output: PenaltyTicketDTO có giá trị penaltyTicketId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public PenaltyTicketDTO getById_toDTO(Long penaltyTicketId) {
              PenaltyTicketEntity penaltyTicketEntity = this.repo.findByPenaltyTicketId(penaltyTicketId).orElse(null);

              if (penaltyTicketEntity != null) {
                     return this.penaltyTicketMapping.toDTO(penaltyTicketEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng PenaltyTicketEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<PenaltyTicketEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<PenaltyTicketEntity> --> List<PenaltyTicketDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<PenaltyTicketDTO> getAll_toDTO() {
              List<PenaltyTicketEntity> listPenaltyTicketEntities = this.repo.findAll();

              List<PenaltyTicketDTO> listPenaltyTicketDTOs = new ArrayList<>();

              if (listPenaltyTicketEntities.isEmpty() == false) {
                     for (PenaltyTicketEntity e : listPenaltyTicketEntities) {
                            listPenaltyTicketDTOs.add(this.penaltyTicketMapping.toDTO(e));
                     }
                     return listPenaltyTicketDTOs;
              }
              return listPenaltyTicketDTOs;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess delete(Long id) {

              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(id);

              if (optional.isPresent()) {
                     Boolean check = isForeignKeyViolationIfDelete(optional.get());

                     if (check) {
                            this.repo.delete(optional.get());
                            return new ResponseBoolAndMess(true, MESS_DELETE_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_DELETE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save(PenaltyTicketEntity entityObj) {

              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(entityObj.getPenaltyTicketId());

              if (optional.isPresent() == false && isForeignKeyEmpty(entityObj) == false) {
                     this.repo.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_SAVE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save_toDTO(PenaltyTicketDTO dtoObj) {
              PenaltyTicketEntity penaltyTicketEntity = this.penaltyTicketMapping.toEntity(dtoObj);

              return save(penaltyTicketEntity);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update(PenaltyTicketEntity entityObj) {

              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(entityObj.getPenaltyTicketId());

              if (optional.isPresent() && isForeignKeyEmpty(entityObj) == false) {
                     this.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_UPDATE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_UPDATE_FAILURE + "," + MESS_OBJECT_NOT_EXIST + " or " + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update_toDTO(PenaltyTicketDTO dtoObj) {

              PenaltyTicketEntity penaltyTicketEntity = this.penaltyTicketMapping.toEntity(dtoObj);

              return update(penaltyTicketEntity);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess invisibleWithoutDelete(Long id) {

              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(id);

              if (optional.isPresent()) {
                     Boolean check = isForeignKeyViolationIfHidden(optional.get());

                     if (check) {
                            PenaltyTicketEntity penaltyTicketEntity = optional.get();
                            penaltyTicketEntity.setIsDelete(true);
                            this.repo.save(penaltyTicketEntity);
                            return new ResponseBoolAndMess(true, MESS_HIDDEN_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_HIDDEN_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional
       @Override
       public Boolean isForeignKeyViolationIfDelete(PenaltyTicketEntity entityObj) {

              // PenaltyTicket has no foreign key
              return true;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyViolationIfHidden(PenaltyTicketEntity entityObj) {

              return true;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyEmpty(PenaltyTicketEntity entityObj) {
              // PenaltyTicket co 2 thuocj tinh khoa ngoai bus_number va driverId
              // kiem tra
              BusEntity busEntity = entityObj.getBusEntity();
              EmployeeEntity employeeEntity = entityObj.getEmployeeEntity();

              if (busEntity != null && employeeEntity != null) {
                     return false;
              }
              return true;
       }

}
