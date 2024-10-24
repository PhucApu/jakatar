package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.BusDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectRepository.EmployeeRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Component
public class BusMapping implements MappingInterface<BusEntity,BusDTO>{

       @Autowired
       private EmployeeRepo employeeRepo;

       @Autowired
       private TicketRepo ticketRepo;

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

              for (EmployeeEntity e : entity.getListEmployeeEntities()) {
                     listEmployeeEntities_Id.add(e.getDriverId());
              }

              for (TicketEntity e : entity.getListTicketEntities()) {
                     listTicketEntities_Id.add(e.getTicketId());
              }

              busDTO.setListEmployeeEntities_Id(listEmployeeEntities_Id);
              busDTO.setListTicketEntities_Id(listTicketEntities_Id);

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

              for (Long value : dto.getListEmployeeEntities_Id()){
                     EmployeeEntity employeeEntity = this.employeeRepo.findByDriverId(value).orElse(null);

                     listEmployeeEntities.add(employeeEntity);
              }
              busEntity.setListEmployeeEntities(listEmployeeEntities);

              List<TicketEntity> listTicketEntities = new ArrayList<>();

              for (Long value : dto.getListTicketEntities_Id()){
                     TicketEntity ticketEntity = this.ticketRepo.findByTicketId(value).orElse(null);

                     listTicketEntities.add(ticketEntity);
              }
              busEntity.setListTicketEntities(listTicketEntities);


              return busEntity;
       }

       
       
}
