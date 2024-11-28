package com.bus_station_ticket.project.ProjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ticket", indexes = @Index(columnList = "ticket_id"))
public class TicketEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "ticket_id")
       private Long ticketId;

       @ManyToOne
       @JoinColumn(name = "username_id", referencedColumnName = "username", nullable = false)
       private AccountEntity accountEntity;

       @ManyToOne
       @JoinColumn(name = "bus_id", referencedColumnName = "bus_id", nullable = false)
       private BusEntity busEntity;

       // @ManyToOne
       // @JoinColumn(name = "routesId", referencedColumnName = "routesId", nullable = false)
       @Column(name = "routesId", nullable = false)
       private Long routesId;

       @ManyToOne
       @JoinColumn(name = "payment_id", referencedColumnName = "payment_id", nullable = false)
       private PaymentEntity paymentEntity;

       @ManyToOne
       @JoinColumn(name = "discount_id", referencedColumnName = "discount_id", nullable = true)
       private DiscountEntity discountEntity;

       @Column(name = "seat_number", nullable = false, columnDefinition = "VARCHAR(10)")
       private String seatNumber;

       @Column(name = "departure_date", nullable = false, columnDefinition = "DATETIME")
       private LocalDate departureDate;

       @Column(name = "price", nullable = false, columnDefinition = "FLOAT", length = 5)
       private float price;

       @Column(name = "phone", nullable = true, columnDefinition = "VARCHAR(11)")
       private String phoneNumber;

       @OneToMany(mappedBy = "ticketEntity", fetch = FetchType.LAZY)
       private List<FeedbackEntity> listFeedbackEntities;

       @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
       private String status;

       @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean isDelete;

       public TicketEntity() {
       }

       public TicketEntity(AccountEntity accountEnitty, BusEntity busEntity, 
       // BusRoutesEntity busRoutesEntity,
                     PaymentEntity paymentEntity, DiscountEntity discountEntity, String seatNumber,
                     LocalDate departureDate, float price, String phoneNumber,
                     List<FeedbackEntity> listFeedbackEntities, Boolean isDelete) {
              this.accountEntity = accountEnitty;
              this.busEntity = busEntity;
              // this.busRoutesEntity = busRoutesEntity;
              this.paymentEntity = paymentEntity;
              this.discountEntity = discountEntity;
              this.seatNumber = seatNumber;
              this.departureDate = departureDate;
              this.price = price;
              this.phoneNumber = phoneNumber;

              this.listFeedbackEntities = listFeedbackEntities;
              this.isDelete = isDelete;
       }

       public Long getTicketId() {
              return ticketId;
       }

       public void setTicketId(Long ticketId) {
              this.ticketId = ticketId;
       }

       public AccountEntity getAccountEntity() {
              return accountEntity;
       }

       public void setAccountEntity(AccountEntity accountEntity) {
              this.accountEntity = accountEntity;
       }

       public BusEntity getBusEntity() {
              return busEntity;
       }

       public void setBusEntity(BusEntity busEntity) {
              this.busEntity = busEntity;
       }

       

       public PaymentEntity getPaymentEntity() {
              return paymentEntity;
       }

       public void setPaymentEntity(PaymentEntity paymentEntity) {
              this.paymentEntity = paymentEntity;
       }

       public DiscountEntity getDiscountEntity() {
              return discountEntity;
       }

       public void setDiscountEntity(DiscountEntity discountEntity) {
              this.discountEntity = discountEntity;
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

       public String getPhoneNumber() {
              return phoneNumber;
       }

       public void setPhoneNumber(String phoneNumber) {
              this.phoneNumber = phoneNumber;
       }

       public List<FeedbackEntity> getListFeedbackEntities() {
              return listFeedbackEntities;
       }

       public void setListFeedbackEntities(List<FeedbackEntity> listFeedbackEntities) {
              this.listFeedbackEntities = listFeedbackEntities;
       }

       public Boolean getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(Boolean isDelete) {
              this.isDelete = isDelete;
       }

       

       @Override
       public String toString() {
              return "TicketEntity [ticketId=" + ticketId + ", accountEntity=" + accountEntity + ", busEntity="
                            + busEntity + ", paymentEntity=" + paymentEntity + ", discountEntity=" + discountEntity
                            + ", seatNumber=" + seatNumber + ", departureDate=" + departureDate + ", price=" + price
                            + ", phoneNumber=" + phoneNumber + ", listFeedbackEntities=" + listFeedbackEntities
                            + ", status=" + status + ", isDelete=" + isDelete + "]";
       }

       public String getStatus() {
              return status;
       }

       public void setStatus(String status) {
              this.status = status;
       }

       public Long getRoutes_Id() {
              return routesId;
       }

       public void setRoutes_Id(Long routesId) {
              this.routesId = routesId;
       }
}
