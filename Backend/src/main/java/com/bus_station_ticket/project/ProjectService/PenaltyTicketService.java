package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.PenaltyTicketDTO;
import com.bus_station_ticket.project.ProjectEntity.PenaltyTicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.PenaltyTicketMapping;
import com.bus_station_ticket.project.ProjectRepository.PenaltyTicketRepo;

@Service
public class PenaltyTicketService {
       
       @Autowired
       private PenaltyTicketRepo repo;

       @Autowired
       private PenaltyTicketMapping penaltyTicketMapping;


       // Lấy một đối tượng PenaltyTicketEntity theo giá trị
       // Input: penaltyTicketId (Long)
       // Output: PenaltyTicketEntity có giá trị penaltyTicketId tương ứng

       public PenaltyTicketEntity getByPenaltyTicketId (Long penaltyTicketId) {
              return this.repo.findByPenaltyTicketId(penaltyTicketId).orElse(null);
       }

       // Mapping đối tượng PenaltyTicketEntity --> PenaltyTicketDTO
       // Input: penaltyTicketId (Long)
       // Output: PenaltyTicketDTO có giá trị penaltyTicketId tương ứng

       public PenaltyTicketDTO getByPenaltyTicketId_toDTO (Long penaltyTicketId){
              PenaltyTicketEntity penaltyTicketEntity = this.repo.findByPenaltyTicketId(penaltyTicketId).orElse(null);

              if(penaltyTicketEntity != null){
                     return this.penaltyTicketMapping.toDTO(penaltyTicketEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng PenaltyTicketEntity
       // Input:
       // Output: List

       public List<PenaltyTicketEntity> getAll () {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<PenaltyTicketEntity> --> List<PenaltyTicketDTO>

       public List<PenaltyTicketDTO> getAll_toDTO () {
              List<PenaltyTicketEntity> listPenaltyTicketEntities = this.repo.findAll();

              List<PenaltyTicketDTO> listPenaltyTicketDTOs = new ArrayList<>();

              if(listPenaltyTicketEntities.isEmpty() == false){
                     for (PenaltyTicketEntity e : listPenaltyTicketEntities){
                            listPenaltyTicketDTOs.add(this.penaltyTicketMapping.toDTO(e));
                     }
                     return listPenaltyTicketDTOs;
              }
              return listPenaltyTicketDTOs;
       }
}
