package com.bus_station_ticket.project.ProjectMappingEntityToDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.DiscountDTO;
import com.bus_station_ticket.project.ProjectEntity.DiscountEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;

@Component
public class DiscountMapping implements MappingInterface<DiscountEntity,DiscountDTO> {

       @Override
       public DiscountDTO toDTO(DiscountEntity entity) {
              
              DiscountDTO discountDTO = new DiscountDTO();
              // Mapping các thuộc tính đơn giản
              discountDTO.setDiscountId(entity.getDiscountId());
              discountDTO.setDiscountPercentage(entity.getDiscountPercentage());
              discountDTO.setValidFrom(entity.getValidFrom());
              discountDTO.setValidUntil(entity.getValidUntil());
              discountDTO.setAmount(entity.getAmount());
              discountDTO.setIsDelete(entity.getIsDelete());

              // Mapping thuộc tính List
              List<Long> listTicketEntities_Id = new ArrayList<>();

              for(TicketEntity e : entity.getListTicketEntities()){
                     listTicketEntities_Id.add(e.getTicketId());
              }

              discountDTO.setListTicketEntities_Id(listTicketEntities_Id);

              return discountDTO;
       }
}
