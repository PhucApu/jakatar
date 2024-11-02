package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.DiscountDTO;
import com.bus_station_ticket.project.ProjectEntity.DiscountEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.DiscountMapping;
import com.bus_station_ticket.project.ProjectRepository.DiscountRepo;

@Service
public class DiscountService {
       
       @Autowired
       private DiscountRepo repo;
       
       @Autowired
       private DiscountMapping discountMapping;
       
       

       // Lấy một đối tượng DiscountEntity theo giá trị discountId
       // Input: discountId (Long)
       // Output: DiscountEntity có giá trị discountId tương ứng
       public DiscountEntity getByDiscountId (Long discountId) {
              return this.repo.findByDiscountId(discountId).orElse(null);
       }

       // Mapping đối tượng DiscountEntity --> DiscountDTO
       // Input: discountId (Long)
       // Output: DiscountDTO có giá trị discountId tương ứng

       public DiscountDTO getByDiscountId_toDTO (Long discountId) {
              DiscountEntity discountEntity = this.repo.findByDiscountId(discountId).orElse(null);

              if(discountEntity != null){
                     return this.discountMapping.toDTO(discountEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng DiscountEntity
       // Input:
       // Output: List

       public List<DiscountEntity> getAll () {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<DiscountEntity> --> List<DiscountDTO>

       public List<DiscountDTO> getAll_toDTO () {
              List<DiscountEntity> listDiscountEntities = this.repo.findAll();

              List<DiscountDTO> listDiscountDTOs = new ArrayList<>();

              if(listDiscountEntities.isEmpty() == false){
                     for(DiscountEntity e : listDiscountEntities){
                            listDiscountDTOs.add(this.discountMapping.toDTO(e));
                     }
                     return listDiscountDTOs;
              }
              return listDiscountDTOs;
       }
       
}
