package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectDTO.PaymentDTO;
import com.bus_station_ticket.project.ProjectEntity.PaymentEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.PaymentMapping;
import com.bus_station_ticket.project.ProjectRepository.PaymentRepo;

@Service
public class PaymentService implements SimpleServiceInf<PaymentEntity, PaymentDTO, Long> {

       @Autowired
       private PaymentRepo repo;

       @Autowired
       private PaymentMapping paymentMapping;

       // Lấy một đối tượng PaymentEntity theo giá trị
       // Input: paymentId (Long)
       // Output: PaymentEntity có giá trị paymentId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public PaymentEntity getById(Long paymentId) {
              return this.repo.findByPaymentId(paymentId).orElse(null);
       }

       // Mapping đối tượng PaymentEntity --> PaymentDTO
       // Input: paymentId (Long)
       // Output: PaymentDTO có giá trị paymentId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public PaymentDTO getById_toDTO(Long paymentId) {
              PaymentEntity paymentEntity = this.repo.findByPaymentId(paymentId).orElse(null);

              if (paymentEntity != null) {
                     return this.paymentMapping.toDTO(paymentEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng PaymentEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<PaymentEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<PaymentEntity> --> List<PaymentDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<PaymentDTO> getAll_toDTO() {
              List<PaymentEntity> listPaymentEntities = this.repo.findAll();

              List<PaymentDTO> listPaymentDTOs = new ArrayList<>();

              if (listPaymentEntities.isEmpty() == false) {
                     for (PaymentEntity e : listPaymentEntities) {
                            listPaymentDTOs.add(this.paymentMapping.toDTO(e));
                     }
                     return listPaymentDTOs;
              }
              return listPaymentDTOs;
       }

       @Override
       public Boolean delete(Long id) {

              // kierm tra
              Optional<PaymentEntity> optional = this.repo.findByPaymentId(id);

              // neu co kq
              if (optional.isPresent()) {
                     // xoa
                     this.repo.delete(optional.get());
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save(PaymentEntity entityObj) {
              // kierm tra
              Optional<PaymentEntity> optional = this.repo.findByPaymentId(entityObj.getPaymentId());

              // neu co kq
              if (optional.isPresent() == false) {
                     // them
                     this.repo.save(entityObj);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save_toDTO(PaymentDTO dtoObj) {
              // kierm tra
              Optional<PaymentEntity> optional = this.repo.findByPaymentId(dtoObj.getPaymentId());

              // neu kq khong co
              if (optional.isPresent() == false) {
                     // mapping
                     PaymentEntity paymentEntity = this.paymentMapping.toEntity(dtoObj);

                     // them
                     this.repo.save(paymentEntity);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update(PaymentEntity entityObj) {

              // kierm tra
              Optional<PaymentEntity> optional = this.repo.findByPaymentId(entityObj.getPaymentId());

              if (optional.isPresent()) {
                     // sua
                     this.repo.save(optional.get());
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update_toDTO(PaymentDTO dtoObj) {
              // kierm tra
              Optional<PaymentEntity> optional = this.repo.findByPaymentId(dtoObj.getPaymentId());

              if (optional.isPresent()) {

                     // mapping
                     PaymentEntity paymentEntity = this.paymentMapping.toEntity(dtoObj);

                     // sua
                     this.repo.save(paymentEntity);
                     return true;
              }

              return false;
       }

}
