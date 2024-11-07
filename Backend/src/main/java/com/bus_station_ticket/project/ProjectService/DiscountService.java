package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectDTO.DiscountDTO;
import com.bus_station_ticket.project.ProjectEntity.DiscountEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.DiscountMapping;
import com.bus_station_ticket.project.ProjectRepository.DiscountRepo;

@Service
public class DiscountService implements SimpleServiceInf<DiscountEntity, DiscountDTO, Long> {

       @Autowired
       private DiscountRepo repo;

       @Autowired
       private DiscountMapping discountMapping;

       // Lấy một đối tượng DiscountEntity theo giá trị discountId
       // Input: discountId (Long)
       // Output: DiscountEntity có giá trị discountId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public DiscountEntity getById(Long discountId) {
              return this.repo.findByDiscountId(discountId).orElse(null);
       }

       // Mapping đối tượng DiscountEntity --> DiscountDTO
       // Input: discountId (Long)
       // Output: DiscountDTO có giá trị discountId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public DiscountDTO getById_toDTO(Long discountId) {
              DiscountEntity discountEntity = this.repo.findByDiscountId(discountId).orElse(null);

              if (discountEntity != null) {
                     return this.discountMapping.toDTO(discountEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng DiscountEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<DiscountEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<DiscountEntity> --> List<DiscountDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<DiscountDTO> getAll_toDTO() {
              List<DiscountEntity> listDiscountEntities = this.repo.findAll();

              List<DiscountDTO> listDiscountDTOs = new ArrayList<>();

              if (listDiscountEntities.isEmpty() == false) {
                     for (DiscountEntity e : listDiscountEntities) {
                            listDiscountDTOs.add(this.discountMapping.toDTO(e));
                     }
                     return listDiscountDTOs;
              }
              return listDiscountDTOs;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean delete(Long id) {

              // Kiem tra xem co ton tai chua
              Optional<DiscountEntity> optional = this.repo.findByDiscountId(id);

              // neu ket qua co
              if (optional.isPresent()) {
                     // xoa
                     this.repo.delete(optional.get());
                     return true;
              }
              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save(DiscountEntity entityObj) {

              // Kiem tra xem da co ton tai chua
              Optional<DiscountEntity> optional = this.repo.findByDiscountId(entityObj.getDiscountId());

              // neu ket qua khong co
              if (optional.isPresent() == false) {

                     // them
                     this.repo.save(entityObj);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public Boolean save_toDTO(DiscountDTO dtoObj) {

              // Kiem tra xem da co ton tai chua
              Optional<DiscountEntity> optional = this.repo.findByDiscountId(dtoObj.getDiscountId());

              // neu ket qua khong co
              if (optional.isPresent() == false) {

                     // Mapping
                     DiscountEntity discountEntity = this.discountMapping.toEntity(dtoObj);
                     // them
                     this.repo.save(discountEntity);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update(DiscountEntity entityObj) {

              // kiem tra xem co ton tai chua
              Optional<DiscountEntity> optional = this.repo.findByDiscountId(entityObj.getDiscountId());

              // neu co ton tai kq
              if (optional.isPresent()) {
                     // sua
                     this.repo.save(entityObj);
                     return true;
              }

              return false;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public Boolean update_toDTO(DiscountDTO dtoObj) {

              // kiem tra co ton tai chua
              Optional<DiscountEntity> optional = this.repo.findByDiscountId(dtoObj.getDiscountId());

              // neu co kq
              if (optional.isPresent()) {
                     // Mapping
                     DiscountEntity discountEntity = this.discountMapping.toEntity(dtoObj);

                     // sua 
                     this.repo.save(discountEntity);
                     return true;
              }

              return false;
       }

}
