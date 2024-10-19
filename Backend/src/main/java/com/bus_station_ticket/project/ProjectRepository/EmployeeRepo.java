package com.bus_station_ticket.project.ProjectRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.bus_station_ticket.project.ProjectEntity.ChoiceEnum;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

       // Truy suất theo thuộc tính
       public Optional<EmployeeEntity> findByDriverId(Long driverId);

       public List<EmployeeEntity> findByIsDriver(ChoiceEnum isDriver);

       public List<EmployeeEntity> findByDriverName(String driverName);

       public Optional<EmployeeEntity> findByLicenseNumber(String licenseNumber);

       public Optional<EmployeeEntity> findByPhoneNumber(String phoneNumber);

       public List<EmployeeEntity> findByIsDelete(ChoiceEnum isDelete);

       @Query(
              value = """
                     SELECT * 
                     FROM employee e, bus b, bus_employee be 
                     WHERE e.driver_id = be.driver_id AND be.bus_id = b.bus_id AND b.bus_id = :busId
              """,
              nativeQuery = true
       )
       public List<EmployeeEntity> findByBusEntityId(@Param("busId") Long busId);

       
}
