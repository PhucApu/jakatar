package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.BusRoutesDTO;
import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.BusRoutesMapping;
import com.bus_station_ticket.project.ProjectRepository.BusRoutesRepo;

@Service
public class BusRoutesService {

       @Autowired
       private BusRoutesRepo repo;
       
       @Autowired
       private BusRoutesMapping busRoutesMapping;


       // Lấy một đối tượng BusRoutesEntity theo giá trị routeId
       // Input: routesId (Long)
       // Output: BusRoutesEntity có giá trị routesId tương ứng
       public BusRoutesEntity getByRoutesId(long routesId) {
              return this.repo.findByRoutesId(routesId).orElse(null);
       }

       // Mapping đối tượng BusRoutesEntity --> BusRoutesDTO
       // Input: routesId (Long)
       // Output: BusRoutesDTO có giá trị routesId tương ứng
       public BusRoutesDTO getByRoutesId_toDTO(Long routesId) {

              BusRoutesEntity busRoutesEntity = this.repo.findByRoutesId(routesId).orElse(null);

              if (busRoutesEntity != null) {
                     return this.busRoutesMapping.toDTO(busRoutesEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng BusRoutesEntity
       // Input:
       // Output: List
       public List<BusRoutesEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<BusRoutesEntity> --> List<BusRoutesDTO>
       public List<BusRoutesDTO> getAll_toDTO() {
              List<BusRoutesEntity> listBusRoutesEntities = this.repo.findAll();

              List<BusRoutesDTO> listBusRoutesDTOs = new ArrayList<>();

              if(listBusRoutesEntities.isEmpty() == false){
                     for (BusRoutesEntity e : listBusRoutesEntities) {
                            listBusRoutesDTOs.add(busRoutesMapping.toDTO(e));
                     }
                     return listBusRoutesDTOs;
              }
              return listBusRoutesDTOs;
       }

}
