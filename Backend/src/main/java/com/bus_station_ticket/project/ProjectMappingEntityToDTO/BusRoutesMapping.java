package com.bus_station_ticket.project.ProjectMappingEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.BusRoutesDTO;
import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

@Component
public class BusRoutesMapping implements MappingInterface<BusRoutesEntity, BusRoutesDTO> {

       @Override
       public BusRoutesDTO toDTO(BusRoutesEntity entity) {

              // Mapping các thuộc tính đơn giản
              BusRoutesDTO busRoutesDTO = new BusRoutesDTO();

              busRoutesDTO.setRoutesId(entity.getRoutesId());
              busRoutesDTO.setDepartureLocation(entity.getDepartureLocation());
              busRoutesDTO.setDestinationLocation(entity.getDestinationLocation());
              busRoutesDTO.setDistanceKilometer(entity.getDistanceKilometer());
              busRoutesDTO.setDepartureTime(entity.getDepartureTime());
              busRoutesDTO.setArivalTime(entity.getArivalTime());
              busRoutesDTO.setPrice(entity.getPrice());
              busRoutesDTO.setIsDelete(entity.getIsDelete());

              // mapping thuộc tính list
              List<Long> listTicketEntities_Id = new ArrayList<>();

              for (TicketEntity e : entity.getListTicketEntities()) {
                     listTicketEntities_Id.add(e.getTicketId());
              }

              return busRoutesDTO;
       }
}
