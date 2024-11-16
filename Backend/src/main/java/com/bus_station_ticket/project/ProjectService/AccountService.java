package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectConfig.ResponseBoolAndMess;
import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEntity;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.AccountMapping;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;
import com.bus_station_ticket.project.ProjectRepository.FeedbackRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;
import com.bus_station_ticket.project.ProjectSecurity.UserDetailsConfig;

@Service
public class AccountService implements SimpleServiceInf<AccountEntity, AccountDTO, String> {

       @Autowired
       private AccountRepo repo;

       @Autowired
       private FeedbackRepo feedbackRepo;

       @Autowired
       private TicketRepo ticketRepo;

       @Autowired
       private AccountMapping accountMapping;

       // Sử dụng để mã hóa mật khẩu
       private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

       // Lấy một đối tượng AccountEntity theo giá trị userName
       // Input: userName (String)
       // Output: AccountEnity có giá trị userName tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public AccountEntity getById(String userName) {

              return this.repo.findByUserName(userName).orElse(null);

       }

       // Mapping đối tượng AccountEnity --> AccountDTO
       // Input: userName (String)
       // Output: AccountEnity có giá trị userName tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public AccountDTO getById_toDTO(String userName) {

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
       @Override
       public List<AccountEntity> getAll() {

              return this.repo.findAll();

       }

       // Mapping đối tượng List<AccountEnity> --> List<AccountDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
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
       @Override
       public ResponseBoolAndMess save(AccountEntity accountEnity) {

              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEntity> optionalAccount = this.repo.findByUserName(accountEnity.getUserName());

              // Nếu kết quả không có
              if (optionalAccount.isPresent() == false && isForeignKeyEmpty(accountEnity) == false) {

                     // Mã hóa mật khẩu trước khi thêm vào csdl
                     AccountEntity accountEntityEncode = encodePassWord(accountEnity);

                     // Thêm AccountEntity vào
                     this.repo.save(accountEntityEncode);
                     return new ResponseBoolAndMess(true, MESS_SAVE_SUCCESS);
              }

              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
       }

       // Thêm một đối tượng AccountEntity vào database
       // Input: AccountDTO (object)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save_toDTO(AccountDTO accountDTO) {

              AccountEntity accountEntity = this.accountMapping.toEntity(accountDTO);

              return save(accountEntity);
       }

       // Sửa một đối tượng AccountEntity
       // Input: AccountEntity (object)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update(AccountEntity accountEnity) {

              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEntity> optionalAccount = this.repo.findByUserName(accountEnity.getUserName());

              // Nếu kết quả có
              if (optionalAccount.isPresent() && isForeignKeyEmpty(accountEnity) == false) {
                     // Mã hóa mật khẩu trước khi thêm vào csdl
                     AccountEntity accountEntityEncode = encodePassWord(accountEnity);

                     // sửa AccountEntity vào
                     this.repo.save(accountEntityEncode);

                     return new ResponseBoolAndMess(true, MESS_UPDATE_SUCCESS);
              }

              return new ResponseBoolAndMess(false,
                            MESS_UPDATE_FAILURE + "," + MESS_OBJECT_NOT_EXIST + " or " + MESS_FOREIGN_KEY_VIOLATION);
       }

       // Mapping thành đối tượng accountDTO
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update_toDTO(AccountDTO accountDTO) {
              // Mapping
              AccountEntity accountEntity = this.accountMapping.toEntity(accountDTO);

              // Nếu kết quả có
              return update(accountEntity);
       }

       // Xóa một đối trượng AccountEntity theo giá trị userName
       // Input: userName (string)
       // Output: boolean
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess delete(String userName) {

              // kiểm tra xem có tồn tại userName chưa
              Optional<AccountEntity> optionalAccount = this.repo.findByUserName(userName);

              // Nếu kết quả có
              if (optionalAccount.isPresent()) {
                     // kiem tra khoa ngoai trước khi xóa
                     Boolean checkForeignKey = foreignKeyViolationIfDelete(optionalAccount.get());

                     if (checkForeignKey) {
                            // Xóa
                            this.repo.delete(optionalAccount.get());
                            return new ResponseBoolAndMess(true, MESS_DELETE_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_DELETE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);

              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess invisibleWithoutDelete(String id) {

              // kiem ta kq
              Optional<AccountEntity> optional = this.repo.findByUserName(id);

              if (optional.isPresent()) {
                     // kiem tra khoa ngoai truoc khi an
                     Boolean checkForeignKey = foreignKeyViolationIfHidden(optional.get());

                     if (checkForeignKey) {
                            AccountEntity accountEntity = optional.get();
                            accountEntity.setIsDelete(true);
                            // cap nhat lai
                            this.save(accountEntity);
                            return new ResponseBoolAndMess(true, MESS_HIDDEN_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_HIDDEN_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);

              }

              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfDelete(AccountEntity entityObj) {

              // Accounnt foreign key Feedback and Ticket

              // lấy những đối tượng ticket và feedback tham chiếu khóa ngoại đến account
              List<TicketEntity> ticketEntities = this.ticketRepo.findByAccountEntity_userName(entityObj.getUserName());

              List<FeedbackEntity> feedbackEntities = this.feedbackRepo
                            .findByAccountEntity_userName(entityObj.getUserName());

              // kiểm tra
              // Nếu có thực thể tham chiếu khóa ngoại
              if (ticketEntities.isEmpty() == false && feedbackEntities.isEmpty() == false) {
                     return false;
              }
              return true;
       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfHidden(AccountEntity entityObj) {

              // Accounnt foreign key Feedback and Ticket

              // lấy những đối tượng ticket và feedback tham chiếu khóa ngoại đến account
              List<TicketEntity> ticketEntities = this.ticketRepo.findByAccountEntity_userName(entityObj.getUserName());

              List<FeedbackEntity> feedbackEntities = this.feedbackRepo
                            .findByAccountEntity_userName(entityObj.getUserName());

              // kiểm tra
              // Nếu có thực thể tham chiếu khóa ngoại
              if (ticketEntities.isEmpty() == false && feedbackEntities.isEmpty() == false) {

                     for (FeedbackEntity feedbackEntity : feedbackEntities) {
                            if (feedbackEntity.getIsDelete() == false) {
                                   return false;
                            }
                     }

                     for (TicketEntity ticketEntity : ticketEntities) {
                            if (ticketEntity.getIsDelete() == false) {
                                   return false;
                            }
                     }
                     return true;
              }
              return true;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyEmpty(AccountEntity entityObj) {

              // Account khong co thuoc tinh khoa ngoai
              return false;
       }

       @Transactional
       public AccountEntity encodePassWord(AccountEntity accountEntity) {

              String pass = accountEntity.getPassWord();
              String encodePass = bCryptPasswordEncoder.encode(pass);
              accountEntity.setPassWord(encodePass);

              return accountEntity;
       }

       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       public AccountDTO getAccountEntityHasLogin() {
              
              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

              if (authentication != null && authentication.isAuthenticated()) {
                     
                     // Lấy ra UserDetails
                     Object principal = authentication.getPrincipal();

                     // kiểm tra
                     if (principal instanceof UserDetails) {

                            // Lấy username
                            String username = ((UserDetailsConfig) principal).getUsername();

                            // Lấy thông tin AccountEntity
                            AccountEntity accountEntity = this.repo.findByUserName(username).orElse(null);

                            return this.accountMapping.toDTO(accountEntity);
                     }
                     return null; 
              }
              return null;
       }
}
