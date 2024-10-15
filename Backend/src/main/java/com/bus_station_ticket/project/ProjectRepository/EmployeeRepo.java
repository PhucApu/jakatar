package com.bus_station_ticket.project.ProjectRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Long>{
       
       // Truy suất theo thuộc tính
       public Optional<List<EmployeeEntity>> findByIsDriver (ChoiceEnum isDriver);
       public Optional<List<EmployeeEntity>> findByDriverName (String driverName);
       public Optional<EmployeeEntity> findByLicenseNumber (String licenseNumber);
       public Optional<EmployeeEntity> findByPhoneNumber (String phoneNumber);
       public Optional<List<EmployeeEntity>> findByIsDelete (ChoiceEnum isDelete);
       
       public Optional<List<BusEntity>> findByListBusEntity (BusEntity busEntity);

       
}
