package com.bus_station_ticket.project.ProjectEntity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "payment", indexes = @Index(columnList = "payment_id"))
public class PaymentEntity {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "payment_id")
       private Long paymentId;

       @Column(name = "payment_time", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime paymentTime;

       @Column(name = "payment_method", nullable = false, columnDefinition = "VARCHAR(20)")
       private String paymentMethod;

       @OneToMany(mappedBy = "paymentEntity", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;

       @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean isDelete;

       

       public PaymentEntity() {
       }

       public PaymentEntity(LocalDateTime paymentTime, String paymentMethod, List<TicketEntity> listTicketEntities,
                     Boolean isDelete) {
              this.paymentTime = paymentTime;
              this.paymentMethod = paymentMethod;
              this.listTicketEntities = listTicketEntities;
              this.isDelete = isDelete;
       }

       public Long getPaymentId() {
              return paymentId;
       }

       public void setPaymentId(Long paymentId) {
              this.paymentId = paymentId;
       }

       public LocalDateTime getPaymentTime() {
              return paymentTime;
       }

       public void setPaymentTime(LocalDateTime paymentTime) {
              this.paymentTime = paymentTime;
       }

       public String getPaymentMethod() {
              return paymentMethod;
       }

       public void setPaymentMethod(String paymentMethod) {
              this.paymentMethod = paymentMethod;
       }

       public List<TicketEntity> getListTicketEntities() {
              return listTicketEntities;
       }

       public void setListTicketEntities(List<TicketEntity> listTicketEntities) {
              this.listTicketEntities = listTicketEntities;
       }

       public Boolean getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(Boolean isDelete) {
              this.isDelete = isDelete;
       }

       @Override
       public String toString() {
              return "PaymentEntity [paymentId=" + paymentId + ", paymentTime=" + paymentTime + ", paymentMethod="
                            + paymentMethod + ", listTicketEntities=" + listTicketEntities + ", isDelete=" + isDelete
                            + "]";
       }
}
