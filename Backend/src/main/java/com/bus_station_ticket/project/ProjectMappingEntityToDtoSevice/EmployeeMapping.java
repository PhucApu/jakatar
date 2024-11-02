package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.EmployeeDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectEntity.PenaltyTicketEntity;
import com.bus_station_ticket.project.ProjectRepository.BusRepo;
import com.bus_station_ticket.project.ProjectRepository.PenaltyTicketRepo;

@Component
public class EmployeeMapping implements MappingInterface<EmployeeEntity, EmployeeDTO> {

       @Autowired
       private BusRepo busRepo;

       @Autowired
       private PenaltyTicketRepo penaltyTicketRepo;

       @Override
       public EmployeeDTO toDTO(EmployeeEntity entity) {

              EmployeeDTO employeeDTO = new EmployeeDTO();
              // Mapping các thuộc tính cơ bản
              employeeDTO.setDriverId(entity.getDriverId());
              employeeDTO.setIsDriver(entity.getIsDriver());
              employeeDTO.setDriverName(entity.getDriverName());
              employeeDTO.setLicenseNumber(entity.getLicenseNumber());
              employeeDTO.setPhoneNumber(entity.getPhoneNumber());
              employeeDTO.setIsDelete(entity.getIsDelete());

              // Mapping các thuộc tính List
              List<Long> listBusEntities_Id = new ArrayList<>();

              if (entity.getListBusEntity() != null) {
                     for (BusEntity e : entity.getListBusEntity()) {
                            listBusEntities_Id.add(e.getBusId());
                     }
              }
              employeeDTO.setListBusEntities_Id(listBusEntities_Id);

              List<Long> listPenaltyTicketEntities_Id = new ArrayList<>();
              if (entity.getListPenaltyTicketEntities() != null) {
                     for (PenaltyTicketEntity e : entity.getListPenaltyTicketEntities()) {
                            listPenaltyTicketEntities_Id.add(e.getPenaltyTicketId());
                     }
              }
              employeeDTO.setListPenaltyTicketEntities_Id(listPenaltyTicketEntities_Id);

              return employeeDTO;
       }

       @Override
       public EmployeeEntity toEntity(EmployeeDTO dto) {

              EmployeeEntity employeeEntity = new EmployeeEntity();
              // Mapping các thuộc tính đơn giản
              employeeEntity.setDriverId(dto.getDriverId());
              employeeEntity.setIsDriver(dto.getIsDriver());
              employeeEntity.setDriverName(dto.getDriverName());
              employeeEntity.setLicenseNumber(dto.getLicenseNumber());
              employeeEntity.setPhoneNumber(dto.getPhoneNumber());
              employeeEntity.setIsDelete(dto.getIsDelete());

              List<BusEntity> listBusEntity = new ArrayList<>();

              if (dto.getListBusEntities_Id() != null) {
                     for (Long value : dto.getListBusEntities_Id()) {
                            BusEntity busEntity = this.busRepo.findByBusId(value).orElse(null);
                            listBusEntity.add(busEntity);
                     }
              }
              employeeEntity.setListBusEntity(listBusEntity);

              List<PenaltyTicketEntity> listPenaltyTicketEntities = new ArrayList<>();
              if (dto.getListPenaltyTicketEntities_Id() != null) {
                     for (Long value : dto.getListPenaltyTicketEntities_Id()) {
                            PenaltyTicketEntity penaltyTicketEntity = this.penaltyTicketRepo
                                          .findByPenaltyTicketId(value)
                                          .orElse(null);
                            listPenaltyTicketEntities.add(penaltyTicketEntity);
                     }
              }
              employeeEntity.setListPenaltyTicketEntities(listPenaltyTicketEntities);

              return employeeEntity;
       }

}
