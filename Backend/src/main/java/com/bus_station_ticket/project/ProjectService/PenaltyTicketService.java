package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectDTO.PenaltyTicketDTO;
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
       public Boolean delete(Long id) {
              
              // kiem tra
              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(id);

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
       public Boolean save(PenaltyTicketEntity entityObj) {
              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(entityObj.getPenaltyTicketId());

              // neu co kq
              if(optional.isPresent() == false){
                     // them
                     this.repo.save(entityObj);
                     return true;
              }
              
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save_toDTO(PenaltyTicketDTO dtoObj) {
              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(dtoObj.getPenaltyTicketId());

              // neu co kq
              if(optional.isPresent() == false){

                     // mapping
                     PenaltyTicketEntity penaltyTicketEntity = this.penaltyTicketMapping.toEntity(dtoObj);

                     // them
                     this.repo.save(penaltyTicketEntity);
                     return true;
              }
              
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update(PenaltyTicketEntity entityObj) {
              
              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(entityObj.getPenaltyTicketId());

              if(optional.isPresent()){
                     // sua
                     this.repo.save(entityObj);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update_toDTO(PenaltyTicketDTO dtoObj) {

              Optional<PenaltyTicketEntity> optional = this.repo.findByPenaltyTicketId(dtoObj.getPenaltyTicketId());

              if(optional.isPresent()){

                     // mapping
                     PenaltyTicketEntity penaltyTicketEntity = this.penaltyTicketMapping.toEntity(dtoObj);
                     // sua
                     this.repo.save(penaltyTicketEntity);
                     return true;
              }

              return false;
       }

}
