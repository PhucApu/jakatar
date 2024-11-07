package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.TicketDTO;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.TicketMapping;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Service
public class TicketService implements SimpleServiceInf<TicketEntity,TicketDTO,Long>{
       
       @Autowired
       private TicketRepo repo;

       @Autowired
       private TicketMapping ticketMapping;

       // Lấy một đối tượng TicketEntity theo giá trị
       // Input: ticketId (Long)
       // Output: TicketEntity có giá trị ticketId tương ứng

       public TicketEntity getById (Long ticketId) {
              return this.repo.findByTicketId(ticketId).orElse(null);
       }

       // Mapping đối tượng TicketEntity --> TicketDTO
       // Input: ticketId (Long)
       // Output: TicketDTO có giá trị ticketId tương ứng

       public TicketDTO getById_toDTO (Long ticketId) {
              TicketEntity ticketEntity = this.repo.findByTicketId(ticketId).orElse(null);

              if(ticketEntity != null){
                     return this.ticketMapping.toDTO(ticketEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng TicketEntity
       // Input:
       // Output: List

       public List<TicketEntity> getAll () {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<TicketEntity> --> List<TicketDTO>

       public List<TicketDTO> getAll_toDTO () {
              List<TicketEntity> listTicketEntities = this.repo.findAll();

              List<TicketDTO> listTicketDTOs = new ArrayList<>();

              if(listTicketEntities .isEmpty() == false){
                     for (TicketEntity e : listTicketEntities){
                            listTicketDTOs.add(this.ticketMapping.toDTO(e));
                     }
                     return listTicketDTOs;
              }
              return listTicketDTOs;
       }

       @Override
       public Boolean delete(Long id) {
              // TODO Auto-generated method stub
              return null;
       }

       @Override
       public Boolean save(TicketEntity entityObj) {
              // TODO Auto-generated method stub
              return null;
       }

       @Override
       public Boolean save_toDTO(TicketDTO dtoObj) {
              // TODO Auto-generated method stub
              return null;
       }

       @Override
       public Boolean update(TicketEntity entityObj) {
              // TODO Auto-generated method stub
              return null;
       }

       @Override
       public Boolean update_toDTO(TicketDTO dtoObj) {
              // TODO Auto-generated method stub
              return null;
       }

       

}
