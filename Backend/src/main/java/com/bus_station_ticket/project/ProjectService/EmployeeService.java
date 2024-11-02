package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.EmployeeDTO;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.EmployeeMapping;
import com.bus_station_ticket.project.ProjectRepository.EmployeeRepo;

@Service
public class EmployeeService {

       @Autowired
       private EmployeeRepo repo;
       
       @Autowired
       private EmployeeMapping employeeMapping;


       // Lấy một đối tượng EmployeeEntity theo giá trị driverId
       // Input: driverId (Long)
       // Output: EmployeeEntity có giá trị driverId tương ứng

       public EmployeeEntity getByDriverId(Long driverId) {
              return this.repo.findByDriverId(driverId).orElse(null);
       }

       // Mapping đối tượng EmployeeEntity --> EmployeeDTO
       // Input: driverId (Long)
       // Output: EmployeeDTO có giá trị driverId tương ứng

       public EmployeeDTO getByDriverId_toDTO(Long driverId) {
              EmployeeEntity employeeEntity = this.repo.findByDriverId(driverId).orElse(null);

              if (employeeEntity != null) {
                     return this.employeeMapping.toDTO(employeeEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng EmployeeEntity
       // Input:
       // Output: List

       public List<EmployeeEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<EmployeeEntity> --> List<EmployeeDTO>

       public List<EmployeeDTO> getAll_toDTO() {
              List<EmployeeEntity> listEmployeeEntities = this.repo.findAll();

              List<EmployeeDTO> listEmployeeDTOs = new ArrayList<>();

              if (listEmployeeEntities.isEmpty() == false) {
                     for (EmployeeEntity e : listEmployeeEntities) {
                            listEmployeeDTOs.add(this.employeeMapping.toDTO(e));
                     }
                     return listEmployeeDTOs;
              }
              return listEmployeeDTOs;
       }

}
