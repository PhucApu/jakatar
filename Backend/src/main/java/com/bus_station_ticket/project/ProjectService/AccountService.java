package com.bus_station_ticket.project.ProjectService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bus_station_ticket.project.ProjectEntity.AccountEnity;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;

@Service
public class AccountService {

       private AccountRepo repo;

       @Autowired
       public AccountService(AccountRepo repo) {
              this.repo = repo;
       }

       // Lấy một đối tượng AccountEntity theo giá trị userName
       // Input: userName (String)
       // Output: AccountEnity có giá trị userName tương ứng
       public AccountEnity getByUserName(String userName) {

              return this.repo.findByUserName(userName).orElse(null);
       }

       // Lấy tất cả các đối tượng AccountEntity
       // Input:
       // Output: List
       public List<AccountEnity> getAll() {

              return this.repo.findAll();
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
