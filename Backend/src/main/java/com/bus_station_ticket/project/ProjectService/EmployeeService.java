package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectDTO.EmployeeDTO;
import com.bus_station_ticket.project.ProjectEntity.EmployeeEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.EmployeeMapping;
import com.bus_station_ticket.project.ProjectRepository.EmployeeRepo;

@Service
public class EmployeeService implements SimpleServiceInf<EmployeeEntity, EmployeeDTO, Long> {

       @Autowired
       private EmployeeRepo repo;

       @Autowired
       private EmployeeMapping employeeMapping;

       // Lấy một đối tượng EmployeeEntity theo giá trị driverId
       // Input: driverId (Long)
       // Output: EmployeeEntity có giá trị driverId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public EmployeeEntity getById(Long driverId) {
              return this.repo.findByDriverId(driverId).orElse(null);
       }

       // Mapping đối tượng EmployeeEntity --> EmployeeDTO
       // Input: driverId (Long)
       // Output: EmployeeDTO có giá trị driverId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public EmployeeDTO getById_toDTO(Long driverId) {
              EmployeeEntity employeeEntity = this.repo.findByDriverId(driverId).orElse(null);

              if (employeeEntity != null) {
                     return this.employeeMapping.toDTO(employeeEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng EmployeeEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<EmployeeEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<EmployeeEntity> --> List<EmployeeDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
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

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean delete(Long id) {
              
              // kiem tra
              Optional<EmployeeEntity> optional = this.repo.findByDriverId(id);

              // neu co kq
              if(optional.isPresent()){
                     // xoa
                     this.repo.delete(optional.get());
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save(EmployeeEntity entityObj) {
              
              // kiem tra xem co ton tai chua
              Optional<EmployeeEntity> optional = this.repo.findByDriverId(entityObj.getDriverId());

              // neu khong co ket qua
              if(optional.isPresent() == false){
                     // them
                     this.repo.save(entityObj);
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save_toDTO(EmployeeDTO dtoObj) {
              
              // kiem tra xem co ton tai chua
              Optional<EmployeeEntity> optional = this.repo.findByDriverId(dtoObj.getDriverId());

              // neu ket qua khong co
              if(optional.isPresent() == false){

                     // mapping
                     EmployeeEntity employeeEntity = this.employeeMapping.toEntity(dtoObj);
                     // them
                     this.repo.save(employeeEntity);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update(EmployeeEntity entityObj) {
              
              // Kiem tra xem co ton tai chua
              
              Optional<EmployeeEntity> optional = this.repo.findByDriverId(entityObj.getDriverId());

              // neu co ton tai
              if(optional.isPresent()){
                     // sua
                     this.repo.save(entityObj);
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update_toDTO(EmployeeDTO dtoObj) {
              
              Optional<EmployeeEntity> optional = this.repo.findByDriverId(dtoObj.getDriverId());

              // neu co ket quan
              if(optional.isPresent()){
                     // mapping
                     EmployeeEntity employeeEntity = this.employeeMapping.toEntity(dtoObj);

                     // sua
                     this.repo.save(employeeEntity);
                     return true;
              }

              return false;
       }

}
