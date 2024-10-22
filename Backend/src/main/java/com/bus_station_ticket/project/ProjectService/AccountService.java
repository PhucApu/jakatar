package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEnity;
import com.bus_station_ticket.project.ProjectMappingEntityToDTO.AccountMapping;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;

@Service
public class AccountService {

       private AccountRepo repo;
       private AccountMapping accountMapping;

       @Autowired
       public AccountService(AccountRepo repo,AccountMapping accountMapping) {
              this.repo = repo;
              this.accountMapping = accountMapping;
       }

       // Lấy một đối tượng AccountEntity theo giá trị userName
       // Input: userName (String)
       // Output: AccountEnity có giá trị userName tương ứng
       public AccountEnity getByUserName(String userName) {

              return this.repo.findByUserName(userName).orElse(null);
       }

       // Mapping đối tượng AccountEnity --> AccountDTO
       // Input: userName (String)
       // Output: AccountEnity có giá trị userName tương ứng
       public AccountDTO getByUserName_toDTO(String userName) {

              AccountEnity accountEnity = this.repo.findByUserName(userName).orElse(null);

              if(accountEnity != null){

                     return this.accountMapping.toDTO(accountEnity);
              }

              return null;
       }

       // Lấy tất cả các đối tượng AccountEntity
       // Input:
       // Output: List
       public List<AccountEnity> getAll() {

              return this.repo.findAll();
       }

       // Mapping đối tượng List<AccountEnity> --> List<AccountDTO>
       public List<AccountDTO> getAll_toDTO(){
              
              List<AccountEnity> listAccountEnities = this.repo.findAll();
              List<AccountDTO> listAccountDTOs = new ArrayList<>();

              // kiểm tra
              if(listAccountEnities.isEmpty() == false){

                     for (AccountEnity e : listAccountEnities) {
                            listAccountDTOs.add(this.accountMapping.toDTO(e));
                     }
                     return listAccountDTOs;
              }
              return listAccountDTOs;
       }

       // Thêm một đối tượng AccountEntity
       // Input: AccountEntity (object)
       // Output: boolean
       public Boolean save(AccountEnity accountEnity){
              
              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEnity> optionalAccount = this.repo.findByUserName(accountEnity.getUserName());

              // Nếu kết quả không có
              if(optionalAccount.isPresent() == false){
                     // Thêm AccountEntity vào
                     this.repo.save(accountEnity);
                     return true;
              }
              return false;
       }

       // Sửa một đối tượng AccountEntity
       // Input: AccountEntity (object)
       // Output: boolean
       public Boolean update(AccountEnity accountEnity){
              
              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEnity> optionalAccount = this.repo.findByUserName(accountEnity.getUserName());

              // Nếu kết quả có
              if(optionalAccount.isPresent()){
                     // Thêm AccountEntity vào
                     this.repo.save(accountEnity);
                     return true;
              }
              return false;
       }

       // Xóa một đối trượng AccountEntity theo giá trị userName
       // Input: userName (string)
       // Output: boolean
       public Boolean delete(String userName){
              
              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEnity> optionalAccount = this.repo.findByUserName(userName);

              // Nếu kết quả có
              if(optionalAccount.isPresent()){
                     
                     // Xóa
                     this.repo.delete(optionalAccount.get());
                     return true;
              }
              return false;
       }

}
