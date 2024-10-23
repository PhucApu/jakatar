package com.bus_station_ticket.project.ProjectMappingEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.EmployeeDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;

@Component
public class EmployeeMapping implements MappingInterface<EmployeeEntity, EmployeeDTO> {

       @Override
       public EmployeeDTO toDTO(EmployeeEntity entity) {

              EmployeeDTO employeeDTO = new EmployeeDTO();
              // Mapping các thuộc tính cơ bản
              employeeDTO.setDriverId(entity.getDriverId());
              employeeDTO.setIsDriver(entity.getIsDriver());
              employeeDTO.setDriverName(entity.getDriverName());
              employeeDTO.setLicenseNumber(entity.getLicenseNumber());
              employeeDTO.setPhoneNumber(entity.getPhoneNumber());
              employeeDTO.setIsDelete(entity.getIsDelete());

              // Mapping các thuộc tính List
              List<Long> listBusEntities_Id = new ArrayList<>();

              for (BusEntity e : entity.getListBusEntity()) {
                     listBusEntities_Id.add(e.getBusId());
              }

              employeeDTO.setListBusEntities_Id(listBusEntities_Id);

              return employeeDTO;
       }
}
