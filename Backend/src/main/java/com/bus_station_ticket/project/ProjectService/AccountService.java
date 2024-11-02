package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.AccountMapping;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;

@Service
public class AccountService {

       @Autowired
       private AccountRepo repo;

       @Autowired
       private AccountMapping accountMapping;

       // Lấy một đối tượng AccountEntity theo giá trị userName
       // Input: userName (String)
       // Output: AccountEnity có giá trị userName tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       public AccountEntity getByUserName(String userName) {

              return this.repo.findByUserName(userName).orElse(null);
       }

       // Mapping đối tượng AccountEnity --> AccountDTO
       // Input: userName (String)
       // Output: AccountEnity có giá trị userName tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       public AccountDTO getByUserName_toDTO(String userName) {

              AccountEntity accountEnity = this.repo.findByUserName(userName).orElse(null);

              if (accountEnity != null) {

                     return this.accountMapping.toDTO(accountEnity);
              }

              return null;
       }

       // Lấy tất cả các đối tượng AccountEntity
       // Input:
       // Output: List
       @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
       public List<AccountEntity> getAll() {

              return this.repo.findAll();
       }

       // Mapping đối tượng List<AccountEnity> --> List<AccountDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       public List<AccountDTO> getAll_toDTO() {

              List<AccountEntity> listAccountEnities = this.repo.findAll();
              List<AccountDTO> listAccountDTOs = new ArrayList<>();

              // kiểm tra
              if (listAccountEnities.isEmpty() == false) {

                     for (AccountEntity e : listAccountEnities) {
                            listAccountDTOs.add(this.accountMapping.toDTO(e));
                     }
                     return listAccountDTOs;
              }
              return listAccountDTOs;
       }

       // Thêm một đối tượng AccountEntity vào database
       // Input: AccountEntity (object)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       public Boolean save(AccountEntity accountEnity) {

              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEntity> optionalAccount = this.repo.findByUserName(accountEnity.getUserName());

              // Nếu kết quả không có
              if (optionalAccount.isPresent() == false) {
                     // Thêm AccountEntity vào
                     this.repo.save(accountEnity);
                     return true;
              }
              return false;
       }

       // Thêm một đối tượng AccountEntity vào database
       // Input: AccountDTO (object)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       public Boolean save_toDTO(AccountDTO accountDTO) {

              AccountEntity accountEntity = this.accountMapping.toEntity(accountDTO);
              if (save(accountEntity)) {
                     return true;
              }
              return false;
       }

       // Sửa một đối tượng AccountEntity
       // Input: AccountEntity (object)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       public Boolean update(AccountEntity accountEnity) {

              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEntity> optionalAccount = this.repo.findByUserName(accountEnity.getUserName());

              // Nếu kết quả có
              if (optionalAccount.isPresent()) {
                     // Thêm AccountEntity vào
                     this.repo.save(accountEnity);
                     return true;
              }
              return false;
       }

       // Xóa một đối trượng AccountEntity theo giá trị userName
       // Input: userName (string)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       public Boolean delete(String userName) {

              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEntity> optionalAccount = this.repo.findByUserName(userName);

              // Nếu kết quả có
              if (optionalAccount.isPresent()) {

                     // Xóa
                     this.repo.delete(optionalAccount.get());
                     return true;
              }
              return false;
       }

}
