package com.bus_station_ticket.project.ProjectMappingEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.PaymentDTO;
import com.bus_station_ticket.project.ProjectEntity.PaymentEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

@Component
public class PaymentMapping implements MappingInterface<PaymentEntity, PaymentDTO> {

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
}
