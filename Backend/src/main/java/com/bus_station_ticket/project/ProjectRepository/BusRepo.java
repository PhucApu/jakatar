package com.bus_station_ticket.project.ProjectRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;

@Repository
public interface BusRepo extends JpaRepository<BusEntity,Long> {
       
       // Truy suất theo thuộc tính
       public Optional<BusEntity> findByBusNumber (String busNumber);
       public Optional<List<BusEntity>> findByCapacity (int capacity);
       public Optional<List<BusEntity>> findByBrand (String brand);
       public Optional<List<BusEntity>> findByIsDelete (ChoiceEnum isDelete);
       public Optional<List<EmployeeEntity>> findByListBusEntity (BusEntity busEntity);

}
