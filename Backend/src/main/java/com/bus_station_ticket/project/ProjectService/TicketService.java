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
import com.bus_station_ticket.project.ProjectDTO.TicketDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEntity;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectEntity.DiscountEntity;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.PaymentEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.TicketMapping;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;
import com.bus_station_ticket.project.ProjectRepository.BusRepo;
import com.bus_station_ticket.project.ProjectRepository.BusRoutesRepo;
import com.bus_station_ticket.project.ProjectRepository.DiscountRepo;
import com.bus_station_ticket.project.ProjectRepository.FeedbackRepo;
import com.bus_station_ticket.project.ProjectRepository.PaymentRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Service
public class TicketService implements SimpleServiceInf<TicketEntity, TicketDTO, Long> {

       @Autowired
       private TicketRepo repo;

       @Autowired
       private AccountRepo accountRepo;

       @Autowired
       private BusRepo busRepo;

       @Autowired
       private DiscountRepo discountRepo;

       @Autowired
       private BusRoutesRepo busRoutesRepo;

       @Autowired
       private PaymentRepo paymentRepo;

       @Autowired
       private FeedbackRepo feedbackRepo;

       @Autowired
       private TicketMapping ticketMapping;

       // Lấy một đối tượng TicketEntity theo giá trị
       // Input: ticketId (Long)
       // Output: TicketEntity có giá trị ticketId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public TicketEntity getById(Long ticketId) {
              return this.repo.findByTicketId(ticketId).orElse(null);
       }

       // Mapping đối tượng TicketEntity --> TicketDTO
       // Input: ticketId (Long)
       // Output: TicketDTO có giá trị ticketId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public TicketDTO getById_toDTO(Long ticketId) {
              TicketEntity ticketEntity = this.repo.findByTicketId(ticketId).orElse(null);

              if (ticketEntity != null) {
                     return this.ticketMapping.toDTO(ticketEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng TicketEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<TicketEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<TicketEntity> --> List<TicketDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<TicketDTO> getAll_toDTO() {
              List<TicketEntity> listTicketEntities = this.repo.findAll();

              List<TicketDTO> listTicketDTOs = new ArrayList<>();

              if (listTicketEntities.isEmpty() == false) {
                     for (TicketEntity e : listTicketEntities) {
                            listTicketDTOs.add(this.ticketMapping.toDTO(e));
                     }
                     return listTicketDTOs;
              }
              return listTicketDTOs;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess delete(Long id) {

              Optional<TicketEntity> optional = this.repo.findByTicketId(id);

              if (optional.isPresent()) {
                     Boolean check = foreignKeyViolationIfDelete(optional.get());

                     if (check) {
                            this.repo.delete(optional.get());
                            return new ResponseBoolAndMess(true, MESS_DELETE_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_DELETE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save(TicketEntity entityObj) {

              // Optional<TicketEntity> optional =
              // this.repo.findByTicketId(entityObj.getTicketId());

              if (isForeignKeyEmpty(entityObj) == false && isRoutesIdVal(entityObj) == true) {
                     entityObj.setTicketId(null);
                     this.repo.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_SAVE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save_toDTO(TicketDTO dtoObj) {

              // kiem tra thuoc tinnh khoa ngoai

              if (isHasForeignKeyEntity(dtoObj)) {
                     TicketEntity ticketEntity = this.ticketMapping.toEntity(dtoObj);

                     return save(ticketEntity);
              }
              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);

       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update(TicketEntity entityObj) {

              Optional<TicketEntity> optional = this.repo.findByTicketId(entityObj.getTicketId());

              if (optional.isPresent() && isForeignKeyEmpty(entityObj) == false && isRoutesIdVal(entityObj) == true) {
                     this.repo.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_UPDATE_SUCCESS);
              }
              return new ResponseBoolAndMess(false,
                            MESS_UPDATE_FAILURE + "," + MESS_OBJECT_NOT_EXIST + " or " + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update_toDTO(TicketDTO dtoObj) {

              // kiem tra thuoc tinnh khoa ngoai

              if (isHasForeignKeyEntity(dtoObj)) {
                     TicketEntity ticketEntity = this.ticketMapping.toEntity(dtoObj);

                     return update(ticketEntity);
              }
              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess invisibleWithoutDelete(Long id) {
              //
              Optional<TicketEntity> optional = this.repo.findByTicketId(id);

              if (optional.isPresent()) {
                     Boolean check = foreignKeyViolationIfHidden(optional.get());

                     if (check) {
                            TicketEntity ticketEntity = optional.get();
                            ticketEntity.setIsDelete(true);
                            this.save(ticketEntity);
                            return new ResponseBoolAndMess(true, MESS_HIDDEN_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_HIDDEN_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfDelete(TicketEntity entityObj) {

              // Ticket foreign key Feedback
              List<FeedbackEntity> feedbackEntities = this.feedbackRepo.findByTicketEntity_Id(entityObj.getTicketId());

              if (feedbackEntities.isEmpty() == false) {
                     return false;
              }

              return true;
       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfHidden(TicketEntity entityObj) {

              // Ticket foreign key Feedback
              List<FeedbackEntity> feedbackEntities = this.feedbackRepo.findByTicketEntity_Id(entityObj.getTicketId());

              if (feedbackEntities.isEmpty() == false) {
                     for (FeedbackEntity e : feedbackEntities) {
                            if (e.getIsDelete() == false) {
                                   return false;
                            }
                     }
                     return true;
              }

              return true;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyEmpty(TicketEntity entityObj) {
              // Ticket co 5 thuoc tinh khoa ngoai: account_name, bus_id, route_id,
              // payment_id, discount_id

              // kiem tra
              AccountEntity accountEntity = entityObj.getAccountEntity();
              BusEntity busEntity = entityObj.getBusEntity();
              Long routesId = entityObj.getRoutes_Id();
              PaymentEntity paymentEntity = entityObj.getPaymentEntity();

              // Do discount co tthe null ne khong can kiem tra
              // DiscountEntity discountEntity = entityObj.getDiscountEntity();

              if (accountEntity != null
                            && busEntity != null
                            && routesId != null
                            && paymentEntity != null) {

                     return false;
              }
              return true;
       }

       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public Boolean isHasForeignKeyEntity(TicketDTO dtoObj) {
              // Ticket co 5 thuoc tinh khoa ngoai: account_name, bus_id, route_id,
              // payment_id, discount_id
              // kiem tra
              AccountEntity accountEntity = this.accountRepo.findByUserName(dtoObj.getAccountEnity_Id()).orElse(null);
              BusEntity busEntity = this.busRepo.findByBusId(dtoObj.getBusEntity_Id()).orElse(null);
              BusRoutesEntity busRoutesEntity = this.busRoutesRepo.findByRoutesId(dtoObj.getBusRoutesEntity_Id())
                            .orElse(null);
              PaymentEntity paymentEntity = this.paymentRepo.findByPaymentId(dtoObj.getPaymentEntity_Id()).orElse(null);
              DiscountEntity discountEntity = this.discountRepo.findByDiscountId(dtoObj.getDiscountEntity_Id())
                            .orElse(null);

              if (accountEntity != null
                            && busEntity != null
                            && busRoutesEntity != null
                            && paymentEntity != null
                            && discountEntity != null) {

                     return true;
              }

              return false;
       }

       // kiểm tra xem routes id có được phân cho bus chạy không
       @Transactional
       public Boolean isRoutesIdVal(TicketEntity ticketEntity) {
              Long routesId = ticketEntity.getRoutes_Id();
              BusEntity busEntity = ticketEntity.getBusEntity();

              if (busEntity.getBusRoutesEntity().getRoutesId().equals(routesId)) {
                     return true;
              }
              return false;
       }

}
