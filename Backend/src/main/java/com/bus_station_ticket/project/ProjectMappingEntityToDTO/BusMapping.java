package com.bus_station_ticket.project.ProjectMappingEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.BusDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

@Component
public class BusMapping implements MappingInterface<BusEntity,BusDTO>{

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
       
       
}
