package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bus_station_ticket.project.ProjectDTO.DiscountDTO;
import com.bus_station_ticket.project.ProjectEntity.DiscountEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;

@Component
public class DiscountMapping implements MappingInterface<DiscountEntity,DiscountDTO> {

       @Autowired
       private TicketRepo ticketRepo;

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

              if(entity.getListTicketEntities() != null){
                     for(TicketEntity e : entity.getListTicketEntities()){
                            listTicketEntities_Id.add(e.getTicketId());
                     }
              }
              discountDTO.setListTicketEntities_Id(listTicketEntities_Id);

              return discountDTO;
       }

       @Override
       public DiscountEntity toEntity(DiscountDTO dto) {
              
              DiscountEntity discountEntity = new DiscountEntity();
              // Mapping cac thuoc tinh co ban
              discountEntity.setDiscountId(dto.getDiscountId());
              discountEntity.setDiscountPercentage(dto.getDiscountPercentage());
              discountEntity.setValidFrom(dto.getValidFrom());
              discountEntity.setValidUntil(dto.getValidUntil());
              discountEntity.setAmount(dto.getAmount());
              discountEntity.setIsDelete(dto.getIsDelete());

              // Mapping cac thuoc tinh list
              List<TicketEntity> listTicketEntities = new ArrayList<>();

              if(dto.getListTicketEntities_Id() != null){
                     for (Long value : dto.getListTicketEntities_Id()){
                            TicketEntity ticketEntity = this.ticketRepo.findByTicketId(value).orElse(null);
                            listTicketEntities.add(ticketEntity);
                     }
              }
              discountEntity.setListTicketEntities(listTicketEntities);

              return discountEntity;
       }

       
}
