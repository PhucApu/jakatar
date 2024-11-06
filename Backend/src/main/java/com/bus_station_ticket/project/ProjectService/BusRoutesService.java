package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectDTO.BusRoutesDTO;
import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.BusRoutesMapping;
import com.bus_station_ticket.project.ProjectRepository.BusRoutesRepo;

@Service
public class BusRoutesService implements SimpleServiceInf<BusRoutesEntity, BusRoutesDTO, Long> {

       @Autowired
       private BusRoutesRepo repo;

       @Autowired
       private BusRoutesMapping busRoutesMapping;

       // Lấy một đối tượng BusRoutesEntity theo giá trị routeId
       // Input: routesId (Long)
       // Output: BusRoutesEntity có giá trị routesId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public BusRoutesEntity getById(Long routesId) {
              return this.repo.findByRoutesId(routesId).orElse(null);
       }

       // Mapping đối tượng BusRoutesEntity --> BusRoutesDTO
       // Input: routesId (Long)
       // Output: BusRoutesDTO có giá trị routesId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public BusRoutesDTO getById_toDTO(Long routesId) {

              BusRoutesEntity busRoutesEntity = this.repo.findByRoutesId(routesId).orElse(null);

              if (busRoutesEntity != null) {
                     return this.busRoutesMapping.toDTO(busRoutesEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng BusRoutesEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<BusRoutesEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<BusRoutesEntity> --> List<BusRoutesDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<BusRoutesDTO> getAll_toDTO() {
              List<BusRoutesEntity> listBusRoutesEntities = this.repo.findAll();

              List<BusRoutesDTO> listBusRoutesDTOs = new ArrayList<>();

              if (listBusRoutesEntities.isEmpty() == false) {
                     for (BusRoutesEntity e : listBusRoutesEntities) {
                            listBusRoutesDTOs.add(busRoutesMapping.toDTO(e));
                     }
                     return listBusRoutesDTOs;
              }
              return listBusRoutesDTOs;
       }

       // Thêm một đối tượng BusRoutesEntity vào database
       // Input: BusRoutesEntity (object)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save(BusRoutesEntity busRoutesEntity) {

              // Kiểm tra
              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(busRoutesEntity.getRoutesId());

              if (optional.isPresent() == false) {

                     this.repo.save(busRoutesEntity);
                     return true;
              }
              return false;
       }

       // Thêm một đối tượng BusRoutesEntity vào database
       // Input: BusRoutesDTO (object)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save_toDTO(BusRoutesDTO busRoutesDTO) {

              BusRoutesEntity busRoutesEntity = this.busRoutesMapping.toEntity(busRoutesDTO);

              // Kiểm tra
              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(busRoutesEntity.getRoutesId());

              if (optional.isPresent() == false) {

                     this.repo.save(busRoutesEntity);
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean delete(Long id) {

              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(id);

              // Neu co ket qua
              if (optional.isPresent()) {
                     // xoa
                     this.repo.delete(optional.get());
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update(BusRoutesEntity entityObj) {
              
              // kiem tra xem co ton tai chua
              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(entityObj.getRoutesId());

              // neu ket qua co
              if(optional.isPresent()){
                     // sua
                     this.repo.save(entityObj);
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update_toDTO(BusRoutesDTO dtoObj) {
              
              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(dtoObj.getRoutesId());

              // neuu ket qua co
              if(optional.isPresent()){
                     // mapping thanh doi tuong entity
                     BusRoutesEntity busRoutesEntity = this.busRoutesMapping.toEntity(dtoObj);

                     // sua doi tuong
                     this.repo.save(busRoutesEntity);
                     return true;
              }

              return false;
       }

}
