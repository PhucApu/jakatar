package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectDTO.BusDTO;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.BusMapping;
import com.bus_station_ticket.project.ProjectRepository.BusRepo;

@Service
public class BusService implements SimpleServiceInf<BusEntity,BusDTO,Long> {
       
       @Autowired
       private BusRepo repo;
       
       @Autowired
       private BusMapping busMapping;

       // Lấy một đối tượng BusEntity theo giá trị busId
       // Input: busId (Long)
       // Output: BusEntity có giá trị busId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public BusEntity getById (Long busIs){
              return this.repo.findByBusId(busIs).orElse(null);
       }

       // Mapping đối tượng BusEntity --> BusDTO
       // Input: busId (Long)
       // Output: BusDTO có giá trị busId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public BusDTO getById_toDTO (Long busId){

              BusEntity busEntity = this.repo.findByBusId(busId).orElse(null);

              if(busEntity != null){
                     return this.busMapping.toDTO(busEntity);
              }
              return null;
       }
       
       // Lấy tất cả các đối tượng BusEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<BusEntity> getAll (){
              return this.repo.findAll();
       }

       // Mapping đối tượng List<BusEntity> --> List<BusEntity>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
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

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean delete(Long id) {
              
              // Kiem tra xem co ton tai chua
              Optional<BusEntity> optional = this.repo.findByBusId(id);

              if(optional.isPresent()){
                     // xoa
                     this.repo.delete(optional.get());
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save(BusEntity entityObj) {
              
              // Kiem ta xem co ton tai chua
              Optional<BusEntity> optional = this.repo.findByBusId(entityObj.getBusId());

              // neu khong co kq
              if(optional.isPresent() == false){
                     // them
                     this.repo.save(entityObj);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save_toDTO(BusDTO dtoObj) {
              
              BusEntity busEntity = this.busMapping.toEntity(dtoObj);

              // Kiem ta xem co ton tai chua
              Optional<BusEntity> optional = this.repo.findByBusId(busEntity.getBusId());

              // neu khong co kq
              if(optional.isPresent() == false){
                     // them
                     this.repo.save(busEntity);
                     return true;
              }

              return false;

       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update(BusEntity entityObj) {
              // kiem tra xem co ton tai chua
              Optional<BusEntity> optional = this.repo.findByBusId(entityObj.getBusId());

              // neu co ket qua
              if(optional.isPresent()){
                     // sua doi tuong
                     this.repo.save(entityObj);
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update_toDTO(BusDTO dtoObj) {
              
              // Kiem tra xem co ton tai chua
              Optional<BusEntity> optional = this.repo.findByBusId(dtoObj.getBusId());

              // neu co ket qua
              if(optional.isPresent()){
                     // Mapping
                     BusEntity busEntity = this.busMapping.toEntity(dtoObj);

                     // sua doi tuong
                     this.repo.save(busEntity);
                     return true;
              }
              return false;
       }

}
