package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.PaymentDTO;
import com.bus_station_ticket.project.ProjectEntity.PaymentEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.PaymentMapping;
import com.bus_station_ticket.project.ProjectRepository.PaymentRepo;

@Service
public class PaymentService {

       @Autowired
       private PaymentRepo repo;

       @Autowired
       private PaymentMapping paymentMapping;

       // Lấy một đối tượng PaymentEntity theo giá trị
       // Input: paymentId (Long)
       // Output: PaymentEntity có giá trị paymentId tương ứng

       public PaymentEntity getByPaymentId(Long paymentId) {
              return this.repo.findByPaymentId(paymentId).orElse(null);
       }

       // Mapping đối tượng PaymentEntity --> PaymentDTO
       // Input: paymentId (Long)
       // Output: PaymentDTO có giá trị paymentId tương ứng

       public PaymentDTO getByPaymentId_toDTO(Long paymentId) {
              PaymentEntity paymentEntity = this.repo.findByPaymentId(paymentId).orElse(null);

              if (paymentEntity != null) {
                     return this.paymentMapping.toDTO(paymentEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng PaymentEntity
       // Input:
       // Output: List

       public List<PaymentEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<PaymentEntity> --> List<PaymentDTO>

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
}
