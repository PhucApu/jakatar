package com.bus_station_ticket.project.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

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
import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEntity;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.AccountMapping;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;
import com.bus_station_ticket.project.ProjectRepository.FeedbackRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;
import com.bus_station_ticket.project.ProjectSecurity.JwtService;
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

       @Autowired
       private JwtService jwtService;

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
                     // kiem tra xem nguoi dung co cap nhat pass khong
                     if (isPassWordUpdate(accountEnity) && foreignKeyViolationIfHidden(accountEnity) == false) {
                            // Mã hóa mật khẩu mới trước khi thêm vào csdl
                            AccountEntity accountEntityEncode = encodePassWord(accountEnity);

                            this.repo.save(accountEntityEncode);
                            return new ResponseBoolAndMess(true, MESS_UPDATE_SUCCESS);
                     }
                     this.repo.save(accountEnity);

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

                     if (checkForeignKey == false) {
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

                     if (checkForeignKey == false) {
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
              List<TicketEntity> ticketEntities = this.ticketRepo
                            .findByAccountEntity_userName(entityObj.getUserName());

              List<FeedbackEntity> feedbackEntities = this.feedbackRepo
                            .findByAccountEntity_userName(entityObj.getUserName());

              // kiểm tra
              // Nếu có thực thể tham chiếu khóa ngoại
              if (ticketEntities.isEmpty() == true && feedbackEntities.isEmpty() == true) {
                     return false;
              }
              return true;

       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfHidden(AccountEntity entityObj) {

              // Accounnt foreign key Feedback and Ticket

              if (entityObj.getIsDelete()) {
                     // lấy những đối tượng ticket và feedback tham chiếu khóa ngoại đến account
                     List<TicketEntity> ticketEntities = this.ticketRepo
                                   .findByAccountEntity_userName(entityObj.getUserName());

                     List<FeedbackEntity> feedbackEntities = this.feedbackRepo
                                   .findByAccountEntity_userName(entityObj.getUserName());

                     // kiểm tra
                     // Nếu có thực thể tham chiếu khóa ngoại
                     if (ticketEntities.isEmpty() == false) {

                            for (TicketEntity ticketEntity : ticketEntities) {
                                   if (ticketEntity.getIsDelete() == false) {
                                          return true;
                                   }
                            }
                     }
                     if (feedbackEntities.isEmpty() == false) {

                            for (FeedbackEntity feedbackEntity : feedbackEntities) {
                                   if (feedbackEntity.getIsDelete() == false) {
                                          return true;
                                   }
                            }
                     }
                     return false;
              }
              return false;

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
       public Boolean isPassWordUpdate(AccountEntity accountEntity) {

              String pass = accountEntity.getPassWord();

              // lay data doi tuong cu
              AccountEntity accountEntityOld = this.repo.findByUserName(accountEntity.getUserName()).orElse(null);

              if (isCurrentHashPass(accountEntityOld.getPassWord(), pass)) {
                     return false;
              }

              // encode
              String passEncocdeNew = bCryptPasswordEncoder.encode(pass);
              String passEncodeOld = accountEntityOld.getPassWord();

              if (passEncocdeNew.equals(passEncodeOld) == true) {
                     return false;
              }
              return true;

       }

       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       public AccountDTO geAccountDTOHasLogin() {

              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

              if (authentication != null && authentication.isAuthenticated()) {

                     // Lấy ra UserDetails
                     Object principal = authentication.getPrincipal();

                     // kiểm tra
                     if (principal instanceof UserDetails) {

                            // Lấy username
                            String username = ((UserDetailsConfig) principal).getUsername();

                            // lay doi tuong duoc xac thuc trong csdl
                            AccountEntity acc = this.repo.findById(username).orElse(null);

                            return this.accountMapping.toDTO(acc);
                     }
                     return null;
              }
              return null;
       }

       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       public String getTokenJwt() {

              Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

              if (authentication != null && authentication.isAuthenticated()) {

                     // Lấy ra UserDetails
                     Object principal = authentication.getPrincipal();

                     // kiểm tra
                     if (principal instanceof UserDetails) {

                            // Lấy username
                            String username = ((UserDetailsConfig) principal).getUsername();

                            // lay doi tuong duoc xac thuc trong csdl
                            AccountEntity acc = this.repo.findById(username).orElse(null);

                            return jwtService.token(acc);
                     }
                     return null;
              }
              return null;
       }

       // Register user
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       public ResponseObject registerAccountUser(String username, String pass, String email, String fullName,
                     String phoneNumber) {

              ResponseObject responseObject = new ResponseObject();

              // Kiểm tra password
              if (isValidPassword(pass) == false) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Register failed. Password is not valid.");
                     responseObject.setData(null);
                     return responseObject;
              }

              // Kiểm tra email
              if (isValidEmail(email) == false ) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Register failed. Email is not valid.");
                     responseObject.setData(null);
                     return responseObject;
              }

              // Kiểm tra fullName
              if (isValidFullName(fullName) == false ) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Register failed. Full name cannot be blank.");
                     responseObject.setData(null);
                     return responseObject;
              }

              // Kiểm tra phoneNumber
              if (isValidPhoneNumber(phoneNumber) == false ) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Register failed. Phone number is not valid.");
                     responseObject.setData(null);
                     return responseObject;
              }


              String role = "ROLE_USER";
              Boolean isDelete = false;
              Boolean isBlock = false;
              List<Long> listFeedbackEntities_Id = new ArrayList<>();
              List<Long> listTicketEntities_Id = new ArrayList<>();

              AccountDTO accountDTO = new AccountDTO(username, pass, email, fullName, phoneNumber, role, isBlock,
                            isDelete, listFeedbackEntities_Id, listTicketEntities_Id);

              if (save_toDTO(accountDTO).getValueBool()) {
                     responseObject.setStatus("success");
                     responseObject.addMessage("mess", "Register success. Now you can login");
                     responseObject.setData(accountDTO);

                     return responseObject;
              }

              responseObject.setStatus("failure");
              responseObject.addMessage("mess", "Register not success.");
              responseObject.setData(accountDTO);

              return responseObject;

       }

       // Register user
       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       public ResponseObject updateAccountForUser(String username, String pass, String email, String fullName,
                     String phoneNumber) {

              ResponseObject responseObject = new ResponseObject();

              String role = "ROLE_USER";
              Boolean isDelete = false;
              Boolean isBlock = false;
              List<Long> listFeedbackEntities_Id = new ArrayList<>();
              List<Long> listTicketEntities_Id = new ArrayList<>();

              // Lấy thông tin user đăng nhập
              AccountDTO accountDTOLogin = geAccountDTOHasLogin();
              if (accountDTOLogin == null) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "You need to login first");
                     responseObject.setData(null);
                     return responseObject;
              }
              String usernameLogin = accountDTOLogin.getUserName();

              if (!usernameLogin.equals(username)) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Cannot update");
                     responseObject.setData(null);
                     return responseObject;
              }

              AccountEntity accountEntity = getById(username);

              // Kiểm tra từng trường dữ liệu
              String passCurrent = pass.equals("no") ? accountEntity.getPassWord() : pass;
              if (!pass.equals("no") && isValidPassword(pass) == false) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Cannot update. Password is not valid.");
                     responseObject.setData(null);
                     return responseObject;
              }

              String emailCurrent = email.equals("no") ? accountEntity.getEmail() : email;
              if (!email.equals("no") && isValidEmail(email) == false) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Cannot update. Email is not valid.");
                     responseObject.setData(null);
                     return responseObject;
              }

              String fullNameCurrent = fullName.equals("no") ? accountEntity.getFullName() : fullName;
              if (!fullName.equals("no") && isValidFullName(fullName) == false) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Cannot update. Full name cannot be blank.");
                     responseObject.setData(null);
                     return responseObject;
              }

              String phoneCurrent = phoneNumber.equals("no") ? accountEntity.getPhoneNumber() : phoneNumber;
              if (!phoneNumber.equals("no") && isValidPhoneNumber(phoneNumber) == false) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "Cannot update. Phone number is not valid.");
                     responseObject.setData(null);
                     return responseObject;
              }

              // Tạo đối tượng AccountDTO với thông tin mới
              AccountDTO accountDTO = new AccountDTO(username, passCurrent, emailCurrent, fullNameCurrent,
                            phoneCurrent, role, isBlock, isDelete, listFeedbackEntities_Id, listTicketEntities_Id);

              // Gọi hàm cập nhật
              if (update_toDTO(accountDTO).getValueBool()) {
                     responseObject.setStatus("success");
                     responseObject.addMessage("mess", "Update success.");
                     responseObject.setData(accountDTO);
                     return responseObject;
              }

              responseObject.setStatus("failure");
              responseObject.addMessage("mess", "Update not successful.");
              responseObject.setData(accountDTO);
              return responseObject;
       }

       public Boolean isCurrentHashPass (String hashPassOld, String hashPassNew){
              if(hashPassOld.equals(hashPassNew)){
                     return true;
              }
              return false;
       }

       public AccountRepo getRepo() {
              return repo;
       }

       public void setRepo(AccountRepo repo) {
              this.repo = repo;
       }

       public FeedbackRepo getFeedbackRepo() {
              return feedbackRepo;
       }

       public void setFeedbackRepo(FeedbackRepo feedbackRepo) {
              this.feedbackRepo = feedbackRepo;
       }

       public TicketRepo getTicketRepo() {
              return ticketRepo;
       }

       public void setTicketRepo(TicketRepo ticketRepo) {
              this.ticketRepo = ticketRepo;
       }

       public AccountMapping getAccountMapping() {
              return accountMapping;
       }

       public void setAccountMapping(AccountMapping accountMapping) {
              this.accountMapping = accountMapping;
       }

       public JwtService getJwtService() {
              return jwtService;
       }

       public void setJwtService(JwtService jwtService) {
              this.jwtService = jwtService;
       }

       public BCryptPasswordEncoder getbCryptPasswordEncoder() {
              return bCryptPasswordEncoder;
       }

       public boolean isValidUserName(String userName) {
              return userName != null && userName.length() >= 5 && userName.length() <= 20;
       }

       public boolean isValidPassword(String password) {
              return password != null && password.length() >= 5;
       }

       public boolean isValidEmail(String email) {
              String emailRegex = "^[a-zA-Z0-9._%+-]+@(gmail|outlook)\\.com$";
              return email != null && Pattern.matches(emailRegex, email);
       }

       public boolean isValidFullName(String fullName) {
              return fullName != null;
       }

       public boolean isValidPhoneNumber(String phoneNumber) {
              String phoneRegex = "^(03|05|07|08|09)[0-9]{8}$";
              return phoneNumber != null && Pattern.matches(phoneRegex, phoneNumber);
       }

       // @Transactional
       // @Override
       // public Boolean isHasForeignKeyEntity(AccountDTO dtoObj) {
       // // Account không có thuộc tinhgs khóa ngoại
       // return true;
       // }

}
