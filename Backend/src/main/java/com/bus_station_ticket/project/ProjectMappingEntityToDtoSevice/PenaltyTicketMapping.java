package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.PenaltyTicketDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectEntity.PenaltyTicketEntity;
import com.bus_station_ticket.project.ProjectRepository.BusRepo;
import com.bus_station_ticket.project.ProjectRepository.EmployeeRepo;

@Component
public class PenaltyTicketMapping implements MappingInterface<PenaltyTicketEntity, PenaltyTicketDTO> {

       @Autowired
       private EmployeeRepo employeeRepo;

       @Autowired
       private BusRepo busRepo;

       @Override
       public PenaltyTicketDTO toDTO(PenaltyTicketEntity entity) {
              PenaltyTicketDTO penaltyTikectDTO = new PenaltyTicketDTO();
              // Mapping các thuộc tính cơ bản
              penaltyTikectDTO.setPenaltyTicketId(entity.getPenaltyTicketId());
              penaltyTikectDTO.setBusEntity_Id(
                            (entity.getBusEntity() != null) ? entity.getBusEntity().getBusId() : null);
              penaltyTikectDTO.setEmployeeEntity_Id(
                            (entity.getEmployeeEntity() != null) ? entity.getEmployeeEntity().getDriverId() : null);
              penaltyTikectDTO.setPenaltyDay(entity.getPenaltyDay());
              penaltyTikectDTO.setDescription(entity.getDescription());
              penaltyTikectDTO.setResponsibility(entity.getResponsibility());
              penaltyTikectDTO.setPrice(entity.getPrice());
              penaltyTikectDTO.setIsDelete(entity.getIsDelete());

              return penaltyTikectDTO;
       }

       @Override
       public PenaltyTicketEntity toEntity(PenaltyTicketDTO dto) {

              PenaltyTicketEntity penaltyTicketEntity = new PenaltyTicketEntity();
              // Mapping các thuộc tính cơ bản
              penaltyTicketEntity.setPenaltyTicketId(dto.getPenaltyTicketId());
              penaltyTicketEntity.setPenaltyDay(dto.getPenaltyDay());
              penaltyTicketEntity.setDescription(dto.getDescription());
              penaltyTicketEntity.setResponsibility(dto.getResponsibility());
              penaltyTicketEntity.setPrice(dto.getPrice());
              penaltyTicketEntity.setIsDelete(dto.getIsDelete());

              BusEntity busEntity = this.busRepo.findByBusId(dto.getBusEntity_Id()).orElse(null);

              penaltyTicketEntity.setBusEntity(busEntity);

              EmployeeEntity employeeEntity = this.employeeRepo.findByDriverId(dto.getEmployeeEntity_Id()).orElse(null);

              penaltyTicketEntity.setEmployeeEntity(employeeEntity);

              return penaltyTicketEntity;
       }

}
