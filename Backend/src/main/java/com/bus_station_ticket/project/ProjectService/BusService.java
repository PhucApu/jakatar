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
import com.bus_station_ticket.project.ProjectDTO.BusDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectEntity.PenaltyTicketEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.BusMapping;
import com.bus_station_ticket.project.ProjectRepository.BusRepo;
import com.bus_station_ticket.project.ProjectRepository.EmployeeRepo;
import com.bus_station_ticket.project.ProjectRepository.PenaltyTicketRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Service
public class BusService implements SimpleServiceInf<BusEntity, BusDTO, Long> {

       @Autowired
       private BusRepo repo;

       @Autowired
       private EmployeeRepo employeeRepo;

       @Autowired
       private PenaltyTicketRepo penaltyTicketRepo;

       @Autowired
       private TicketRepo ticketRepo;

       @Autowired
       private BusMapping busMapping;

       // Lấy một đối tượng BusEntity theo giá trị busId
       // Input: busId (Long)
       // Output: BusEntity có giá trị busId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public BusEntity getById(Long busIs) {
              return  this.repo.findByBusId(busIs).orElse(null);
              
       }

       // Mapping đối tượng BusEntity --> BusDTO
       // Input: busId (Long)
       // Output: BusDTO có giá trị busId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public BusDTO getById_toDTO(Long busId) {

              BusEntity busEntity = this.repo.findByBusId(busId).orElse(null);

              if (busEntity != null) {
                     return this.busMapping.toDTO(busEntity);
                     
              }
              return null;
       }

       // Lấy tất cả các đối tượng BusEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<BusEntity>  getAll() {
              return this.repo.findAll();
              
       }

       // Mapping đối tượng List<BusEntity> --> List<BusEntity>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<BusDTO> getAll_toDTO() {
              List<BusEntity> listBusEntities = this.repo.findAll();
              List<BusDTO> listBusDTOs = new ArrayList<>();

              if (listBusEntities.isEmpty() == false) {
                     for (BusEntity e : listBusEntities) {
                            listBusDTOs.add(this.busMapping.toDTO(e));
                     }
                     return  listBusDTOs;
              }
              return  listBusDTOs;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess delete(Long id) {

              Optional<BusEntity> optional = this.repo.findByBusId(id);

              if (optional.isPresent()) {
                     Boolean check = isForeignKeyViolationIfDelete(optional.get());

                     if (check) {
                            // xoa
                            this.repo.delete(optional.get());
                            return new ResponseBoolAndMess(true, MESS_DELETE_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_DELETE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save(BusEntity entityObj) {

              Optional<BusEntity> optional = this.repo.findByBusId(entityObj.getBusId());

              if (optional.isPresent() == false && isForeignKeyEmpty(entityObj) == false) {
                     this.repo.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_SAVE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save_toDTO(BusDTO dtoObj) {

              BusEntity busEntity = this.busMapping.toEntity(dtoObj);

              return save(busEntity);

       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update(BusEntity entityObj) {
              Optional<BusEntity> optional = this.repo.findByBusId(entityObj.getBusId());

              if (optional.isPresent() && isForeignKeyEmpty(entityObj) == false) {
                     this.repo.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_UPDATE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_UPDATE_FAILURE + "," + MESS_OBJECT_NOT_EXIST + " or " + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update_toDTO(BusDTO dtoObj) {

              BusEntity busEntity = this.busMapping.toEntity(dtoObj);

              return update(busEntity);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess invisibleWithoutDelete(Long id) {

              Optional<BusEntity> optional = this.repo.findByBusId(id);

              if (optional.isPresent()) {
                     // kiem tra
                     Boolean check = isForeignKeyViolationIfHidden(optional.get());

                     if (check) {
                            BusEntity busEntity = optional.get();
                            busEntity.setIsDelete(true);

                            this.repo.save(busEntity);
                            return new ResponseBoolAndMess(true, MESS_HIDDEN_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_HIDDEN_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);

       }

       @Transactional
       @Override
       public Boolean isForeignKeyViolationIfDelete(BusEntity entityObj) {

              // Bus foreign key employee, ticket, penalty_tiket

              List<EmployeeEntity> employeeEntities = this.employeeRepo.findByBusEntityId(entityObj.getBusId());

              List<TicketEntity> ticketEntities = this.ticketRepo.findByBusEntity_Id(entityObj.getBusId());

              List<PenaltyTicketEntity> penaltyTicketEntities = this.penaltyTicketRepo
                            .findByBusEntity_Id(entityObj.getBusId());

              // kiem tra
              if (employeeEntities.isEmpty() == false && ticketEntities.isEmpty() == false
                            && penaltyTicketEntities.isEmpty() == false) {
                     return false;
              }
              return true;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyViolationIfHidden(BusEntity entityObj) {
              // Bus foreign key employee, ticket, penalty_tiket

              List<EmployeeEntity> employeeEntities = this.employeeRepo.findByBusEntityId(entityObj.getBusId());

              List<TicketEntity> ticketEntities = this.ticketRepo.findByBusEntity_Id(entityObj.getBusId());

              List<PenaltyTicketEntity> penaltyTicketEntities = this.penaltyTicketRepo
                            .findByBusEntity_Id(entityObj.getBusId());

              // kiem tra
              if (employeeEntities.isEmpty() == false && ticketEntities.isEmpty() == false
                            && penaltyTicketEntities.isEmpty() == false) {
                     
                     for (PenaltyTicketEntity penaltyTicketEntity : penaltyTicketEntities) {
                            if(penaltyTicketEntity.getIsDelete() == false){
                                   return false;
                            }
                     }

                     for(TicketEntity e :  ticketEntities){
                            if(e.getIsDelete() == false){
                                   return false;
                            }
                     }
                     
                     for(EmployeeEntity e : employeeEntities){
                            if(e.getIsDelete() == false){
                                   return false;
                            }
                     }
                     return true;
              }
              return true;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyEmpty(BusEntity entityObj) {
              // Bus khong co thuoc tinh khoa ngoai
              return false;
       }

       

}
