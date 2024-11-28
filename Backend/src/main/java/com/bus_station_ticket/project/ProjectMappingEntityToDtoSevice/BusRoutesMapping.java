package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.BusRoutesDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
// import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectRepository.BusRepo;
// import com.bus_station_ticket.project.ProjectRepository.BusRoutesRepo;
// import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Component
public class BusRoutesMapping implements MappingInterface<BusRoutesEntity, BusRoutesDTO> {

       // @Autowired
       // private TicketRepo ticketRepo;

       @Autowired
       private BusRepo busRepo;

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
              // List<Long> listTicketEntities_Id = new ArrayList<>();

              // if (entity.getListTicketEntities() != null) {
              //        for (TicketEntity e : entity.getListTicketEntities()) {
              //               listTicketEntities_Id.add(e.getTicketId());
              //        }
              // }

              // busRoutesDTO.setListTicketEntities_Id(listTicketEntities_Id);

              List<Long> listBusEntities_Id = new ArrayList<>();
              if(entity.getListBusEntities() != null){
                     for(BusEntity e : entity.getListBusEntities()){
                            listBusEntities_Id.add(e.getBusId());
                     }
              }
              busRoutesDTO.setListtBusEntities_Id(listBusEntities_Id);

              return busRoutesDTO;
       }

       @Override
       public BusRoutesEntity toEntity(BusRoutesDTO dto) {

              BusRoutesEntity busRoutesEntity = new BusRoutesEntity();
              // Mapping các thuộc tính đơn giản
              busRoutesEntity.setRoutesId(dto.getRoutesId());
              busRoutesEntity.setDepartureLocation(dto.getDepartureLocation());
              busRoutesEntity.setDestinationLocation(dto.getDestinationLocation());
              busRoutesEntity.setDistanceKilometer(dto.getDistanceKilometer());
              busRoutesEntity.setDepartureTime(dto.getDepartureTime());
              busRoutesEntity.setArivalTime(dto.getArivalTime());
              busRoutesEntity.setPrice(dto.getPrice());
              busRoutesEntity.setIsDelete(dto.getIsDelete());

              // Mapping cac thuoo tinh list
              // List<TicketEntity> listTicketEntities = new ArrayList<>();

              // if (dto.getListTicketEntities_Id() != null) {

              //        for (Long value : dto.getListTicketEntities_Id()) {
              //               TicketEntity ticketEntity = this.ticketRepo.findByTicketId(value).orElse(null);
              //               listTicketEntities.add(ticketEntity);
              //        }
              // }
              // busRoutesEntity.setListTicketEntities(listTicketEntities);

              List<BusEntity> listBusEntities = new ArrayList<>();
              if(dto.getListtBusEntities_Id() != null){
                     for(Long id: dto.getListtBusEntities_Id()){
                            BusEntity busEntity = this.busRepo.findByBusId(id).orElse(null);
                            listBusEntities.add(busEntity);
                     }
              }
              busRoutesEntity.setListBusEntities(listBusEntities);

              return busRoutesEntity;
       }

}
