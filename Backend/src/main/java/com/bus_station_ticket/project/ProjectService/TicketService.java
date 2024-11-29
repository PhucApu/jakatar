package com.bus_station_ticket.project.ProjectService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bus_station_ticket.project.ProjectConfig.ResponseBoolAndMess;
import com.bus_station_ticket.project.ProjectConfig.ResponseObject;
import com.bus_station_ticket.project.ProjectDTO.AccountDTO;
import com.bus_station_ticket.project.ProjectDTO.TicketDTO;
import com.bus_station_ticket.project.ProjectEntity.AccountEntity;
import com.bus_station_ticket.project.ProjectEntity.BusEntity;
import com.bus_station_ticket.project.ProjectEntity.BusRoutesEntity;
import com.bus_station_ticket.project.ProjectEntity.DiscountEntity;
import com.bus_station_ticket.project.ProjectEntity.FeedbackEntity;
import com.bus_station_ticket.project.ProjectEntity.PaymentEntity;
import com.bus_station_ticket.project.ProjectEntity.TicketEntity;
import com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice.TicketMapping;
import com.bus_station_ticket.project.ProjectRepository.BusRoutesRepo;
import com.bus_station_ticket.project.ProjectRepository.DiscountRepo;
import com.bus_station_ticket.project.ProjectRepository.FeedbackRepo;
import com.bus_station_ticket.project.ProjectRepository.PaymentRepo;
import com.bus_station_ticket.project.ProjectRepository.TicketRepo;
import com.bus_station_ticket.project.VNPay.VNPayService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TicketService implements SimpleServiceInf<TicketEntity, TicketDTO, Long> {

       @Autowired
       private TicketRepo repo;

       @Autowired
       private FeedbackRepo feedbackRepo;

       @Autowired
       private BusRoutesRepo busRoutesRepo;

       @Autowired
       private PaymentService paymentService;

       @Autowired
       private PaymentRepo paymentRepo;

       @Autowired
       private DiscountRepo discountRepo;

       @Autowired
       private BusService busService;

       @Autowired
       private AccountService accountService;

       @Autowired
       private TicketMapping ticketMapping;

       @Autowired
       private VNPayService vnPayService;

       // Lấy một đối tượng TicketEntity theo giá trị
       // Input: ticketId (Long)
       // Output: TicketEntity có giá trị ticketId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public TicketEntity getById(Long ticketId) {
              return this.repo.findByTicketId(ticketId).orElse(null);
       }

       // Mapping đối tượng TicketEntity --> TicketDTO
       // Input: ticketId (Long)
       // Output: TicketDTO có giá trị ticketId tương ứng
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public TicketDTO getById_toDTO(Long ticketId) {
              TicketEntity ticketEntity = this.repo.findByTicketId(ticketId).orElse(null);

              if (ticketEntity != null) {
                     return this.ticketMapping.toDTO(ticketEntity);
              }
              return null;
       }

       // Lấy tất cả các đối tượng TicketEntity
       // Input:
       // Output: List
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<TicketEntity> getAll() {
              return this.repo.findAll();
       }

       // Mapping đối tượng List<TicketEntity> --> List<TicketDTO>
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       @Override
       public List<TicketDTO> getAll_toDTO() {
              List<TicketEntity> listTicketEntities = this.repo.findAll();

              List<TicketDTO> listTicketDTOs = new ArrayList<>();

              if (listTicketEntities.isEmpty() == false) {
                     for (TicketEntity e : listTicketEntities) {
                            listTicketDTOs.add(this.ticketMapping.toDTO(e));
                     }
                     return listTicketDTOs;
              }
              return listTicketDTOs;
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess delete(Long id) {

              Optional<TicketEntity> optional = this.repo.findByTicketId(id);

              if (optional.isPresent()) {
                     Boolean check = foreignKeyViolationIfDelete(optional.get());

                     if (check == false) {
                            this.repo.delete(optional.get());
                            return new ResponseBoolAndMess(true, MESS_DELETE_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_DELETE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save(TicketEntity entityObj) {

              // Optional<TicketEntity> optional =
              // this.repo.findByTicketId(entityObj.getTicketId());

              if (isForeignKeyEmpty(entityObj) == false && isRoutesIdVal(entityObj) == true) {
                     entityObj.setTicketId(null);
                     this.repo.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_SAVE_SUCCESS);
              }
              return new ResponseBoolAndMess(false, MESS_SAVE_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess save_toDTO(TicketDTO dtoObj) {

              // kiem tra thuoc tinnh khoa ngoai

              TicketEntity ticketEntity = this.ticketMapping.toEntity(dtoObj);

              return save(ticketEntity);

       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update(TicketEntity entityObj) {

              Optional<TicketEntity> optional = this.repo.findByTicketId(entityObj.getTicketId());

              if (optional.isPresent() && isForeignKeyEmpty(entityObj) == false && isRoutesIdVal(entityObj) == true) {
                     if(foreignKeyViolationIfHidden(entityObj)){
                            return new ResponseBoolAndMess(true, MESS_FOREIGN_KEY_VIOLATION);
                     }
                     entityObj.setTicketId(null);
                     this.repo.save(entityObj);
                     return new ResponseBoolAndMess(true, MESS_UPDATE_SUCCESS);
              }
              return new ResponseBoolAndMess(false,
                            MESS_UPDATE_FAILURE + "," + MESS_OBJECT_NOT_EXIST + " or " + MESS_FOREIGN_KEY_VIOLATION);
       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess update_toDTO(TicketDTO dtoObj) {

              // kiem tra thuoc tinnh khoa ngoai

              TicketEntity ticketEntity = this.ticketMapping.toEntity(dtoObj);

              return update(ticketEntity);

       }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       @Override
       public ResponseBoolAndMess invisibleWithoutDelete(Long id) {
              //
              Optional<TicketEntity> optional = this.repo.findByTicketId(id);

              if (optional.isPresent()) {
                     Boolean check = foreignKeyViolationIfHidden(optional.get());

                     if (check == false) {
                            TicketEntity ticketEntity = optional.get();
                            ticketEntity.setIsDelete(true);
                            this.repo.save(ticketEntity);
                            return new ResponseBoolAndMess(true, MESS_HIDDEN_SUCCESS);
                     }
                     return new ResponseBoolAndMess(false, MESS_HIDDEN_FAILURE + "," + MESS_FOREIGN_KEY_VIOLATION);
              }
              return new ResponseBoolAndMess(false, MESS_OBJECT_NOT_EXIST);
       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfDelete(TicketEntity entityObj) {

              // Ticket foreign key Feedback
              List<FeedbackEntity> feedbackEntities = this.feedbackRepo.findByTicketEntity_Id(entityObj.getTicketId());

              if (feedbackEntities.isEmpty() == false) {
                     return true;
              }

              return false;
       }

       @Transactional
       @Override
       public Boolean foreignKeyViolationIfHidden(TicketEntity entityObj) {

              // Ticket foreign key Feedback
              List<FeedbackEntity> feedbackEntities = this.feedbackRepo.findByTicketEntity_Id(entityObj.getTicketId());

              if (feedbackEntities.isEmpty() == false) {
                     for (FeedbackEntity e : feedbackEntities) {
                            if (e.getIsDelete() == false) {
                                   return true;
                            }
                     }
                     return false;
              }

              return false;
       }

       @Transactional
       @Override
       public Boolean isForeignKeyEmpty(TicketEntity entityObj) {
              // Ticket co 5 thuoc tinh khoa ngoai: account_name, bus_id, route_id,
              // payment_id, discount_id

              // kiem tra
              AccountEntity accountEntity = entityObj.getAccountEntity();
              BusEntity busEntity = entityObj.getBusEntity();
              Long routesId = entityObj.getRoutes_Id();
              PaymentEntity paymentEntity = entityObj.getPaymentEntity();

              // Do discount co tthe null ne khong can kiem tra
              // DiscountEntity discountEntity = entityObj.getDiscountEntity();

              if (accountEntity != null
                            && busEntity != null
                            && routesId != null
                            && paymentEntity != null) {

                     return false;
              }
              return true;
       }

       // @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation
       // = Isolation.READ_COMMITTED)
       // @Override
       // public Boolean isHasForeignKeyEntity(TicketDTO dtoObj) {
       // // Ticket co 5 thuoc tinh khoa ngoai: account_name, bus_id, route_id,
       // // payment_id, discount_id
       // // kiem tra
       // AccountEntity accountEntity =
       // this.accountRepo.findByUserName(dtoObj.getAccountEnity_Id()).orElse(null);
       // BusEntity busEntity =
       // this.busRepo.findByBusId(dtoObj.getBusEntity_Id()).orElse(null);
       // BusRoutesEntity busRoutesEntity =
       // this.busRoutesRepo.findByRoutesId(dtoObj.getBusRoutesEntity_Id())
       // .orElse(null);
       // PaymentEntity paymentEntity =
       // this.paymentRepo.findByPaymentId(dtoObj.getPaymentEntity_Id()).orElse(null);
       // DiscountEntity discountEntity =
       // this.discountRepo.findByDiscountId(dtoObj.getDiscountEntity_Id())
       // .orElse(null);

       // if (accountEntity != null
       // && busEntity != null
       // && busRoutesEntity != null
       // && paymentEntity != null
       // && discountEntity != null) {

       // return true;
       // }

       // return false;
       // }

       // kiểm tra xem routes id có được phân cho bus chạy không
       @Transactional
       public Boolean isRoutesIdVal(TicketEntity ticketEntity) {
              Long routesId = ticketEntity.getRoutes_Id();
              BusEntity busEntity = ticketEntity.getBusEntity();

              if (busEntity.getBusRoutesEntity().getRoutesId().equals(routesId)) {
                     return true;
              }
              return false;
       }

       // thong kê
       @Transactional(propagation = Propagation.REQUIRED, readOnly = true, isolation = Isolation.READ_COMMITTED)
       public ResponseObject statisticTicketRangeDay(LocalDateTime dateA, LocalDateTime dateB) {

              ResponseObject responseObject = new ResponseObject();

              // Lấy dữ liệu tất cả các vé trong phạm vi ngày A và ngày B
              List<TicketEntity> ticketEntities = this.repo.findTicketsWithinDateRange(dateA, dateB);

              if (ticketEntities.isEmpty() == false) {
                     responseObject.setStatus("success");
                     List<TicketDTO> ticketDTOs = new ArrayList<>();

                     // mapping
                     for (TicketEntity ticketEntity : ticketEntities) {
                            ticketDTOs.add(this.ticketMapping.toDTO(ticketEntity));
                     }

                     responseObject.setData(ticketDTOs);
                     responseObject.addMessage("mess", "Statistics from day " + dateA + " to day " + dateB);
                     responseObject.addMessage("size", ticketEntities.size());

                     // doanh thu va so luong cac ve da duoc thanh toan thanh cong
                     int countTicketSuccess = 0;
                     float sumMoney = 0;

                     // so luong cac ve dang cho thanh toan (pending)
                     int countTicketPending = 0;

                     // so luong cac ve dang thanh toan that bai (failure)
                     int countTicketFailure = 0;

                     // mang dem cac chuyen co trong ve
                     List<Long> listRoutesId = new ArrayList<>();

                     for (TicketEntity e : ticketEntities) {

                            if (listRoutesId.contains(e.getRoutes_Id()) == false) {
                                   listRoutesId.add(e.getRoutes_Id());
                            }

                            if (e.getStatus().equals("success")) {
                                   countTicketSuccess++;
                                   sumMoney += e.getPrice();
                            }
                            if (e.getStatus().equals("pending")) {
                                   countTicketPending++;
                            }
                            if (e.getStatus().equals("failure")) {
                                   countTicketFailure++;
                            }
                     }

                     responseObject.addMessage("numberTicketSuccess", countTicketSuccess);
                     responseObject.addMessage("sumMoneyTicketSuccess", sumMoney);

                     responseObject.addMessage("numberTicketPending", countTicketPending);

                     responseObject.addMessage("numberTicketFailure", countTicketFailure);

                     // doanh thu cua cac chuyen
                     List<Object> list = new ArrayList<>();
                     Map<String, Float> routes = new HashMap<>();

                     for (Long id : listRoutesId) {

                            float sumMoneyRoutes = 0;

                            for (TicketEntity e : ticketEntities) {
                                   if (e.getRoutes_Id().equals(id) && e.getStatus().equals("success")) {
                                          sumMoneyRoutes += e.getPrice();
                                   }
                            }
                            // mapping de lay thong tin routes
                            BusRoutesEntity busRoutesEntity = this.busRoutesRepo.findByRoutesId(id).orElse(null);

                            String inforRoutes = busRoutesEntity.getRoutesId() + "-"
                                          + busRoutesEntity.getDepartureLocation() + "-"
                                          + busRoutesEntity.getDestinationLocation();

                            routes.put(inforRoutes, sumMoneyRoutes);
                     }
                     list.add(routes);

                     responseObject.addMessage("revenueOfRoutes", list);

                     return responseObject;
              }

              responseObject.setStatus("failure");
              responseObject.setData(ticketEntities);
              responseObject.addMessage("mess", "There are no statistics from day " + dateA + " to day " + dateB);

              return responseObject;
       }

       // // Hàm đặt vé
       // @Transactional(propagation = Propagation.REQUIRED, isolation =
       // Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       // public ResponseObject createTicketAndPayment(HttpServletRequest request,
       // String returnUrl, String seat,
       // Long busId, String departureLocation,
       // String destinationLocation, LocalDateTime departureTime, LocalDateTime
       // arivalTime, Long discountId,
       // String token) throws Exception {

       // ResponseObject responseObject = new ResponseObject();

       // // kiểm tra ghế có hợp lệ không

       // // Kiểm tra ghế có trôngs không
       // ResponseBoolAndMess check = this.busService.isValSeat(seat, busId,
       // departureLocation, destinationLocation,
       // departureTime, arivalTime);

       // if (check.getValueBool()) {

       // // Phân giải token lấy username
       // AccountDTO accountDTO = this.accountService.geAccountDTOHasLogin();

       // // Lấy thông tin chuyến của bus chạy
       // BusRoutesEntity busRoutesEntity =
       // this.busRoutesRepo.findByBusEntity_Id(busId).orElse(null);

       // // Lấy thông tin của discount
       // DiscountEntity discountEntity = null;
       // float discountPercentage = 0;
       // if (discountId != null) {
       // discountEntity = this.discountRepo.findByDiscountId(discountId).orElse(null);
       // if (discountEntity != null) {

       // // lay phan tram giam gia
       // discountPercentage = discountEntity.getDiscountPercentage();

       // // giam so luong
       // discountEntity.setAmount(discountEntity.getAmount() - 1);
       // }
       // }

       // float finalAmount = 0;

       // LocalDateTime now = LocalDateTime.now();

       // // Tạo payment
       // PaymentEntity paymentEntity = new PaymentEntity();
       // paymentEntity.setPaymentTime(now);
       // paymentEntity.setOriginalAmount(busRoutesEntity.getPrice());

       // if (discountEntity != null) {
       // // tinh tien giam gia
       // float discountAmount = busRoutesEntity.getPrice() * discountPercentage;

       // finalAmount = busRoutesEntity.getPrice() - busRoutesEntity.getPrice() *
       // discountPercentage;

       // // set
       // paymentEntity.setDiscountAmount(discountAmount);

       // paymentEntity.setFinalAmount(finalAmount);
       // } else {
       // paymentEntity.setDiscountAmount(0);

       // finalAmount = busRoutesEntity.getPrice();

       // paymentEntity.setFinalAmount(finalAmount);
       // }

       // paymentEntity.setPaymentMethod("VNPay");

       // paymentEntity.setStatus("pending");

       // paymentEntity.setListTicketEntities(new ArrayList<>());

       // paymentEntity.setIsDelete(false);

       // // them payment vao db
       // ResponseBoolAndMess checkAddPayment =
       // this.paymentService.save(paymentEntity);

       // if (checkAddPayment.getValueBool()) {
       // // Tạo ticket để khóa ghế đó lại
       // TicketDTO ticketDTO = new TicketDTO();

       // ticketDTO.setAccountEnity_Id(accountDTO.getUserName());
       // ticketDTO.setBusEntity_Id(busId);
       // ticketDTO.setBusRoutesEntity_Id(busRoutesEntity.getRoutesId());
       // ticketDTO.setPaymentEntity_Id(paymentEntity.getPaymentId());
       // ticketDTO.setDiscountEntity_Id(
       // discountEntity != null ? discountEntity.getDiscountId() : null);
       // ticketDTO.setSeatNumber(seat);
       // ticketDTO.setDepartureDate(departureTime);

       // ticketDTO.setPrice(finalAmount);
       // ticketDTO.setPhoneNumber(accountDTO.getPhoneNumber());

       // ticketDTO.setStatus("pending");

       // ticketDTO.setListFeedbackEntities_Id(new ArrayList<>());

       // ticketDTO.setIsDelete(false);

       // // luu vao db
       // TicketEntity ticketEntity =
       // this.repo.save(this.ticketMapping.toEntity(ticketDTO));

       // // Neu luu ve thanh cong
       // if (ticketEntity != null) {

       // // gọi đến VNPayService

       // // chuyen ma hoa don qua string
       // String ticketId = String.valueOf(ticketEntity.getTicketId());
       // String url = this.vnPayService.createOrder(request, finalAmount, ticketId,
       // returnUrl);

       // responseObject.setStatus("success");
       // responseObject.setData(url);
       // responseObject.addMessage("mess", "Link VNPay");
       // return responseObject;
       // }
       // }else{
       // throw new Exception("Lỗi giao dịch");
       // }

       // } else {
       // responseObject.setStatus("failure");
       // responseObject.setData(seat);
       // responseObject.addMessage("mess", "The seat is already occupied, please
       // reserve another seat");

       // return responseObject;
       // }

       // }

       @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
       public ResponseObject createTicketAndPayment(HttpServletRequest request, String returnUrl, String seat,
                     Long busId, String departureLocation,
                     String destinationLocation, LocalDateTime departureTime, LocalDateTime arivalTime, Long discountId,
                     String token) throws Exception {

              ResponseObject responseObject = new ResponseObject();

              // Kiểm tra ghế có hợp lệ không
              ResponseBoolAndMess check = this.busService.isValSeat(seat, busId, departureLocation, destinationLocation,
                            departureTime, arivalTime);

              if (!check.getValueBool()) {
                     responseObject.setStatus("failure");
                     responseObject.addMessage("mess", "The seat is already occupied, please reserve another seat");
                     return responseObject;
              }

              // Phân giải token lấy username
              AccountDTO accountDTO = this.accountService.geAccountDTOHasLogin();

              // Lấy thông tin chuyến của bus chạy
              BusRoutesEntity busRoutesEntity = this.busRoutesRepo.findByBusEntity_Id(busId)
                            .orElseThrow(() -> new IllegalArgumentException("Bus route not found for busId: " + busId));

              // Lấy thông tin discount
              DiscountEntity discountEntity = discountId != null
                            ? this.discountRepo.findByDiscountId(discountId).orElse(null)
                            : null;
              float discountPercentage = (discountEntity != null) ? discountEntity.getDiscountPercentage() : 0;

              // Giảm số lượng discount
              if (discountEntity != null) {
                     discountEntity.setAmount(discountEntity.getAmount() - 1);
              }

              // Tính toán giá trị thanh toán
              // Tính toán giá trị thanh toán
              BigDecimal originalPrice = BigDecimal.valueOf(busRoutesEntity.getPrice());
              BigDecimal discountAmount = originalPrice.multiply(BigDecimal.valueOf(discountPercentage))
                            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
              BigDecimal finalAmount = originalPrice.subtract(discountAmount);

              // Tạo payment
              PaymentEntity paymentEntity = new PaymentEntity();
              paymentEntity.setPaymentTime(LocalDateTime.now());
              paymentEntity.setOriginalAmount(originalPrice.floatValue());
              paymentEntity.setDiscountAmount(discountAmount.floatValue());
              paymentEntity.setFinalAmount(finalAmount.floatValue());
              paymentEntity.setPaymentMethod("VNPay");
              paymentEntity.setStatus("pending");
              paymentEntity.setIsDelete(false);

              PaymentEntity savedPayment = this.paymentRepo.save(paymentEntity);
              if (savedPayment == null || savedPayment.getPaymentId() == null) {
                     throw new RuntimeException("Failed to save payment entity.");
              }

              // Tạo ticket để khóa ghế
              TicketDTO ticketDTO = new TicketDTO();
              ticketDTO.setTicketId(1L);
              ticketDTO.setAccountEnity_Id(accountDTO.getUserName());
              ticketDTO.setBusEntity_Id(busId);
              ticketDTO.setBusRoutesEntity_Id(busRoutesEntity.getRoutesId());
              ticketDTO.setPaymentEntity_Id(savedPayment.getPaymentId());
              ticketDTO.setDiscountEntity_Id(discountEntity != null ? discountEntity.getDiscountId() : null);
              ticketDTO.setSeatNumber(seat);
              ticketDTO.setDepartureDate(departureTime);
              ticketDTO.setPrice(finalAmount.floatValue());
              ticketDTO.setPhoneNumber(accountDTO.getPhoneNumber());
              ticketDTO.setStatus("pending");
              ticketDTO.setIsDelete(false);

              ticketDTO.setListFeedbackEntities_Id(new ArrayList<>());

              TicketEntity ticketEntity = this.ticketMapping.toEntity(ticketDTO);
              ticketEntity.setTicketId(null);

              TicketEntity savedTicket = this.repo.save(ticketEntity);

              if (savedTicket == null || savedTicket.getTicketId() == null) {
                     throw new RuntimeException("Failed to save ticket entity.");
              }

              // Gọi VNPayService

              String ticketId = String.valueOf(savedTicket.getTicketId());
              String url = this.vnPayService.createOrder(request, finalAmount.intValue(),
                            String.valueOf(savedPayment.getPaymentId()), ticketId, returnUrl);

              // String url = this.vnPayService.createOrder(request, convertToNumeric(100000),
              // "test 2", returnUrl);

              responseObject.setStatus("success");
              responseObject.setData(url);
              responseObject.addMessage("mess", "Link VNPay");
              return responseObject;
       }

       // Hàm xử lý giao dịch trả về
       public ResponseObject returnFronVNPay(HttpServletRequest request) {

              ResponseObject responseObject = new ResponseObject();

              Map<String, String> result = this.vnPayService.orderReturn(request);

              // Kiểm tra kết quả giao dịch
              String vnp_ResponseCode = result.get("vnp_ResponseCode");
              String vnp_TransactionStatus = result.get("vnp_TransactionStatus");

              // Lấy mã payment
              Long paymentId = Long.parseLong(result.get("vnp_TxnRef"));

              // Nếu thành công
              if (vnp_ResponseCode.equals(vnp_TransactionStatus)) {

                     // Cập nhật trạng thái payment
                     PaymentEntity paymentEntity = this.paymentService.getById(paymentId);
                     paymentEntity.setStatus("success");

                     PaymentEntity paymentEntity2 = this.paymentRepo.save(paymentEntity);

                     // cap nhatj trang thai ticket
                     if (paymentEntity2.getStatus().equals("success")) {
                            List<TicketEntity> ticketEntities = this.repo.findByPaymentEntity_Id(paymentId);

                            for (TicketEntity e : ticketEntities) {
                                   e.setStatus("success");
                                   this.repo.save(e);
                            }

                            responseObject.setStatus("success");
                            responseObject.setData(ticketEntities);
                            responseObject.addMessage("mess", "Payment successful");

                            return responseObject;
                     }

              }

              // Cập nhật trạng thái payment
              PaymentEntity paymentEntity = this.paymentService.getById(paymentId);
              paymentEntity.setStatus("failure");
              PaymentEntity paymentEntity2 = this.paymentRepo.save(paymentEntity);

              // cap nhatj trang thai ticket
              if (paymentEntity2.getStatus().equals("failure")) {
                     List<TicketEntity> ticketEntities = this.repo.findByPaymentEntity_Id(paymentId);

                     for (TicketEntity e : ticketEntities) {
                            e.setStatus("failure");
                            this.repo.save(e);
                     }

                     responseObject.setStatus("failure");
                     responseObject.setData(ticketEntities);
                     responseObject.addMessage("mess", "Payment no successful");
              }

              return responseObject;
       }

}
