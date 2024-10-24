package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.PaymentDTO;
import com.bus_station_ticket.project.ProjectEntity.PaymentEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Component
public class PaymentMapping implements MappingInterface<PaymentEntity, PaymentDTO> {

       @Autowired
       private TicketRepo ticketRepo;

       @Override
       public PaymentDTO toDTO(PaymentEntity entity) {

              PaymentDTO paymentDTO = new PaymentDTO();
              // Mapping các thuộc tính cơ bản
              paymentDTO.setPaymentId(entity.getPaymentId());
              paymentDTO.setPaymentTime(entity.getPaymentTime());
              paymentDTO.setPaymentMethod(entity.getPaymentMethod());
              paymentDTO.setIsDelete(entity.getIsDelete());

              // Mapping các thuộc tính List
              List<Long> listTicketEntities_Id = new ArrayList<>();

              for (TicketEntity e : entity.getListTicketEntities()) {
                     listTicketEntities_Id.add(e.getTicketId());
              }

              paymentDTO.setListTicketEntities_Id(listTicketEntities_Id);

              return paymentDTO;
       }

       @Override
       public PaymentEntity toEntity(PaymentDTO dto) {
              
              PaymentEntity paymentEntity = new PaymentEntity();
              // Mapping các thuọc tính cơ bản
              paymentEntity.setPaymentId(dto.getPaymentId());
              paymentEntity.setPaymentTime(dto.getPaymentTime());
              paymentEntity.setPaymentMethod(dto.getPaymentMethod());
              paymentEntity.setIsDelete(dto.getIsDelete());

              // Mapping các thuộc tính List
              List<TicketEntity> listTicketEntities = new ArrayList<>();

              for (Long value : dto.getListTicketEntities_Id()){
                     TicketEntity ticketEntity = this.ticketRepo.findByTicketId(value).orElse(null);

                     listTicketEntities.add(ticketEntity);
              }
              paymentEntity.setListTicketEntities(listTicketEntities);

              return paymentEntity;
       }

       
}
