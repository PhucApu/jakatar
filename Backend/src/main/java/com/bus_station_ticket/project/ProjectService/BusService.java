package com.bus_station_ticket.project.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

       // Mapping đối tượng BusEntity --> AccountDTO
       // Input: userName (String)
       // Output: AccountEnity có giá trị userName tương ứng

       


}
