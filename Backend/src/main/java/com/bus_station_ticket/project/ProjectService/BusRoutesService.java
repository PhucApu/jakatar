package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectConfig.ResponseBoolAndMess;
import com.bus_station_ticket.project.ProjectDTO.BusRoutesDTO;
import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.BusRoutesMapping;
import com.bus_station_ticket.project.ProjectRepository.BusRoutesRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Service
public class BusRoutesService implements SimpleServiceInf<BusRoutesEntity, BusRoutesDTO, Long> {

       @Autowired
       private BusRoutesRepo repo;

       @Autowired
       private TicketRepo ticketRepo;

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
                     return  listBusRoutesDTOs;
              }
              return  listBusRoutesDTOs;
       }

       // Thêm một đối tượng BusRoutesEntity vào database
       // Input: BusRoutesEntity (object)
       // Output: ResponseBoolAndMess
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save(BusRoutesEntity busRoutesEntity) {

              // Kiểm tra
              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(busRoutesEntity.getRoutesId());

              if (optional.isPresent() == false && isForeignKeyEmpty(busRoutesEntity) == false) {

                     this.repo.save(busRoutesEntity);
                     return new ResponseBoolAndMess(true, MESS_SAVE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
       }

       // Thêm một đối tượng BusRoutesEntity vào database
       // Input: BusRoutesDTO (object)
       // Output: ResponseBoolAndMess
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save_toDTO(BusRoutesDTO busRoutesDTO) {

              // mapping
              BusRoutesEntity busRoutesEntity2 = this.busRoutesMapping.toEntity(busRoutesDTO);

              return save(busRoutesEntity2);

       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess delete(Long id) {

              // kiem tra
              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(id);

              // neu ket qua co
              if (optional.isPresent()) {
                     // kierm tra khoa ngoai truoc khi xoa
                     Boolean check = foreignKeyViolationIfDelete(optional.get());

                     if (check) {
                            // xoa
                            this.repo.delete(optional.get());
                            return new ResponseBoolAndMess(true, MESS_DELETE_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_DELETE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update(BusRoutesEntity entityObj) {

              // Kiem tra
              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(entityObj.getRoutesId());

              if (optional.isPresent() && isForeignKeyEmpty(entityObj) == false) {

                     this.repo.save(entityObj);

                     return new ResponseBoolAndMess(true, MESS_UPDATE_SUCCESS);
              }

              return new ResponseBoolAndMess(false, MESS_UPDATE_FAILURE + "," + MESS_OBJECT_NOT_EXIST + " or " + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update_toDTO(BusRoutesDTO dtoObj) {

              BusRoutesEntity busRoutesEntity = this.busRoutesMapping.toEntity(dtoObj);

              return update(busRoutesEntity);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess invisibleWithoutDelete(Long id) {

              Optional<BusRoutesEntity> optional = this.repo.findByRoutesId(id);

              if (optional.isPresent()) {
                     // kiem tra khoa ngoai
                     Boolean check = foreignKeyViolationIfHidden(optional.get());

                     if (check) {
                            BusRoutesEntity busRoutesEntity = optional.get();
                            busRoutesEntity.setIsDelete(true);
                            this.repo.save(busRoutesEntity);

                            return new ResponseBoolAndMess(true, MESS_HIDDEN_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_HIDDEN_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfDelete(BusRoutesEntity entityObj) {

              // BusRoutes foreign key ticket

              // lay danh sach ticket tham chieu den BusRoutes duoc xoa
              List<TicketEntity> ticketEntities = this.ticketRepo.findByBusEntity_Id(entityObj.getRoutesId());

              // kiem tra
              // neu co thuc the
              if (ticketEntities.isEmpty() == false) {
                     return false;
              }

              return true;
       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfHidden(BusRoutesEntity entityObj) {
              // BusRoutes foriegn key ticket

              // lay danh sach ticket tham chieu den BusRoutes duoc xoa
              List<TicketEntity> ticketEntities = this.ticketRepo.findByBusEntity_Id(entityObj.getRoutesId());

              // kiem tra
              // neu co thuc the
              if (ticketEntities.isEmpty() == false) {
                     for (TicketEntity ticketEntity : ticketEntities) {
                            if (ticketEntity.getIsDelete() == false) {
                                   return false;
                            }
                     }
                     return true;
              }

              return true;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyEmpty(BusRoutesEntity entityObj) {
              // BusRouter khong co thuoc tinh khoa ngoai
              return false;
       }

       

}
