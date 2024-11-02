package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.BusDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectEntity.PenaltyTicketEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectRepository.EmployeeRepo;
import com.bus_station_ticket.project.ProjectRepository.PenaltyTicketRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Component
public class BusMapping implements MappingInterface<BusEntity, BusDTO> {

       @Autowired
       private EmployeeRepo employeeRepo;

       @Autowired
       private TicketRepo ticketRepo;

       @Autowired
       private PenaltyTicketRepo penaltyTicketRepo;

       @Override
       public BusDTO toDTO(BusEntity entity) {

              BusDTO busDTO = new BusDTO();
              // Mapping các thuộc tính cơ bản
              busDTO.setBusId(entity.getBusId());
              busDTO.setBusNumber(entity.getBusNumber());
              busDTO.setCapacity(entity.getCapacity());
              busDTO.setBrand(entity.getBrand());
              busDTO.setIsDelete(entity.getIsDelete());

              // Mapping các thuộc tính list
              List<Long> listEmployeeEntities_Id = new ArrayList<>();
              List<Long> listTicketEntities_Id = new ArrayList<>();
              List<Long> listPenaltyTicketEntities_Id = new ArrayList<>();

              if (entity.getListEmployeeEntities() != null) {
                     for (EmployeeEntity e : entity.getListEmployeeEntities()) {
                            listEmployeeEntities_Id.add(e.getDriverId());
                     }
              }

              if (entity.getListTicketEntities() != null) {
                     for (TicketEntity e : entity.getListTicketEntities()) {
                            listTicketEntities_Id.add(e.getTicketId());
                     }
              }

              if (entity.getListPenaltyTicketEntities() != null) {
                     for (PenaltyTicketEntity e : entity.getListPenaltyTicketEntities()) {
                            listPenaltyTicketEntities_Id.add(e.getPenaltyTicketId());
                     }
              }

              busDTO.setListEmployeeEntities_Id(listEmployeeEntities_Id);
              busDTO.setListTicketEntities_Id(listTicketEntities_Id);
              busDTO.setListPenaltyTicketEntities_Id(listPenaltyTicketEntities_Id);

              return busDTO;
       }

       @Override
       public BusEntity toEntity(BusDTO dto) {

              BusEntity busEntity = new BusEntity();

              // Mapping các thuộc tính cơ bản
              busEntity.setBusId(dto.getBusId());
              busEntity.setBusNumber(dto.getBusNumber());
              busEntity.setCapacity(dto.getCapacity());
              busEntity.setBrand(dto.getBrand());
              busEntity.setIsDelete(dto.getIsDelete());

              // Mapping các thộc tính List
              List<EmployeeEntity> listEmployeeEntities = new ArrayList<>();

              if (dto.getListEmployeeEntities_Id() != null) {
                     for (Long value : dto.getListEmployeeEntities_Id()) {
                            EmployeeEntity employeeEntity = this.employeeRepo.findByDriverId(value).orElse(null);
                            listEmployeeEntities.add(employeeEntity);
                     }
              }
              busEntity.setListEmployeeEntities(listEmployeeEntities);

              List<TicketEntity> listTicketEntities = new ArrayList<>();
              if (dto.getListTicketEntities_Id() != null) {
                     for (Long value : dto.getListTicketEntities_Id()) {
                            TicketEntity ticketEntity = this.ticketRepo.findByTicketId(value).orElse(null);
                            listTicketEntities.add(ticketEntity);
                     }
              }
              busEntity.setListTicketEntities(listTicketEntities);

              List<PenaltyTicketEntity> listPenaltyTicketEntities = new ArrayList<>();
              if (dto.getListPenaltyTicketEntities_Id() != null) {
                     for (Long value : dto.getListPenaltyTicketEntities_Id()) {
                            PenaltyTicketEntity penaltyTicketEntity = this.penaltyTicketRepo
                                          .findByPenaltyTicketId(value).orElse(null);
                            listPenaltyTicketEntities.add(penaltyTicketEntity);
                     }
              }
              busEntity.setListPenaltyTicketEntities(listPenaltyTicketEntities);

              return busEntity;
       }

}
