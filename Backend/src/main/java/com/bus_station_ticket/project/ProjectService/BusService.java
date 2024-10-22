package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.BusDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDTO.BusMapping;
import com.bus_station_ticket.project.ProjectRepository.BusRepo;

@Service
public class BusService {
       
       private BusRepo repo;
       private BusMapping busMapping;

       @Autowired
       public BusService(BusRepo repo, BusMapping busMapping) {
              this.repo = repo;
              this.busMapping = busMapping;
       }

       // Lấy một đối tượng BusEntity theo giá trị busId
       // Input: busId (Long)
       // Output: BusEntity có giá trị busId tương ứng
       public BusEntity getByBusId (long busIs){
              return this.repo.findByBusId(busIs).orElse(null);
       }

       // Mapping đối tượng BusEntity --> BusDTO
       // Input: busId (Long)
       // Output: BusDTO có giá trị busId tương ứng
       public BusDTO getByBusId_toDTO (Long busId){

              BusEntity busEntity = this.repo.findByBusId(busId).orElse(null);

              if(busEntity != null){
                     return this.busMapping.toDTO(busEntity);
              }
              return null;
       }
       
       // Lấy tất cả các đối tượng BusEntity
       // Input:
       // Output: List
       public List<BusEntity> getAll (){
              return this.repo.findAll();
       }

       // Mapping đối tượng List<BusEntity> --> List<BusEntity>
       public List<BusDTO> getAll_toDTO (){
              List<BusEntity> listBusEntities = this.repo.findAll();
              List<BusDTO> listBusDTOs = new ArrayList<>();

              if(listBusEntities.isEmpty() == false){
                     for (BusEntity e : listBusEntities) {
                            listBusDTOs.add(this.busMapping.toDTO(e));
                     }
              }
              return listBusDTOs;
       }

       // Thêm một đối tượng BusEntity
       // Input: BusEntity (object)
       // Output: boolean



       // Sửa một đối tượng BusEntity
       // Input: BusEntity (object)
       // Output: boolean


       // Xóa một đối trượng BusEntity theo giá trị userName
       // Input: userName (string)
       // Output: boolean

}
