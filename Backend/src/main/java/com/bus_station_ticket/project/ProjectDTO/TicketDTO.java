package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDate;
import java.util.List;


public class TicketDTO {

       private Long ticketId;
       private String accountEnity_Id;
       private Long busEntity_Id;
       private Long busRoutesEntity_Id;
       private Long paymentEntity_Id;
       private Long discountEntity_Id;
       private String seatNumber;
       private LocalDate departureDate;
       private float price;
       private String userName;
       private String phoneNumber;
       private String email; 
       private List<Long> listFeedbackEntities_Id;
       private Boolean isDelete;

       public TicketDTO() {
       }
       
       public TicketDTO(Long ticketId, String accountEnity_Id, Long busEntity_Id, Long busRoutesEntity_Id,
                     Long paymentEntity_Id, Long discountEntity_Id, String seatNumber, LocalDate departureDate,
                     float price, String userName, String phoneNumber, String email, List<Long> listFeedbackEntities_Id,
                     Boolean isDelete) {
              this.ticketId = ticketId;
              this.accountEnity_Id = accountEnity_Id;
              this.busEntity_Id = busEntity_Id;
              this.busRoutesEntity_Id = busRoutesEntity_Id;
              this.paymentEntity_Id = paymentEntity_Id;
              this.discountEntity_Id = discountEntity_Id;
              this.seatNumber = seatNumber;
              this.departureDate = departureDate;
              this.price = price;
              this.userName = userName;
              this.phoneNumber = phoneNumber;
              this.email = email;
              this.listFeedbackEntities_Id = listFeedbackEntities_Id;
              this.isDelete = isDelete;
       }

       public Long getTicketId() {
              return ticketId;
       }
       public void setTicketId(Long ticketId) {
              this.ticketId = ticketId;
       }
       public String getAccountEnity_Id() {
              return accountEnity_Id;
       }
       public void setAccountEnity_Id(String accountEnity_Id) {
              this.accountEnity_Id = accountEnity_Id;
       }
       public Long getBusEntity_Id() {
              return busEntity_Id;
       }
       public void setBusEntity_Id(Long busEntity_Id) {
              this.busEntity_Id = busEntity_Id;
       }
       public Long getBusRoutesEntity_Id() {
              return busRoutesEntity_Id;
       }
       public void setBusRoutesEntity_Id(Long busRoutesEntity_Id) {
              this.busRoutesEntity_Id = busRoutesEntity_Id;
       }
       public Long getPaymentEntity_Id() {
              return paymentEntity_Id;
       }
       public void setPaymentEntity_Id(Long paymentEntity_Id) {
              this.paymentEntity_Id = paymentEntity_Id;
       }
       public Long getDiscountEntity_Id() {
              return discountEntity_Id;
       }
       public void setDiscountEntity_Id(Long discountEntity_Id) {
              this.discountEntity_Id = discountEntity_Id;
       }
       public String getSeatNumber() {
              return seatNumber;
       }
       public void setSeatNumber(String seatNumber) {
              this.seatNumber = seatNumber;
       }
       public LocalDate getDepartureDate() {
              return departureDate;
       }
       public void setDepartureDate(LocalDate departureDate) {
              this.departureDate = departureDate;
       }
       public float getPrice() {
              return price;
       }
       public void setPrice(float price) {
              this.price = price;
       }
       public String getUserName() {
              return userName;
       }
       public void setUserName(String userName) {
              this.userName = userName;
       }
       public String getPhoneNumber() {
              return phoneNumber;
       }
       public void setPhoneNumber(String phoneNumber) {
              this.phoneNumber = phoneNumber;
       }
       public String getEmail() {
              return email;
       }
       public void setEmail(String email) {
              this.email = email;
       }
       public List<Long> getListFeedbackEntities_Id() {
              return listFeedbackEntities_Id;
       }
       public void setListFeedbackEntities_Id(List<Long> listFeedbackEntities_Id) {
              this.listFeedbackEntities_Id = listFeedbackEntities_Id;
       }
       public Boolean getIsDelete() {
              return isDelete;
       }
       public void setIsDelete(Boolean isDelete) {
              this.isDelete = isDelete;
       }

       
}
