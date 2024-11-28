package com.bus_station_ticket.project.ProjectEntity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment", indexes = @Index(columnList = "payment_id"))
public class PaymentEntity {
       @Id
       // @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "payment_id")
       private Long paymentId;

       @Column(name = "payment_time", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime paymentTime;

       @Column(name = "original_amount", nullable = false)
       private float originalAmount;

       @Column(name = "discount_amount", nullable = false)
       private float discountAmount;

       @Column(name = "final_amount", nullable = false)
       private float finalAmount;

       @Column(name = "payment_method", nullable = false, columnDefinition = "VARCHAR(20)")
       private String paymentMethod;

       @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
       private String status;

       @OneToMany(mappedBy = "paymentEntity", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;

       @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean isDelete;

       public PaymentEntity() {
       }

       

       public PaymentEntity(LocalDateTime paymentTime, float originalAmount, float discountAmount, float finalAmount,
                     String paymentMethod, String status, List<TicketEntity> listTicketEntities, Boolean isDelete) {
              this.paymentTime = paymentTime;
              this.originalAmount = originalAmount;
              this.discountAmount = discountAmount;
              this.finalAmount = finalAmount;
              this.paymentMethod = paymentMethod;
              this.status = status;
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



       public float getOriginalAmount() {
              return originalAmount;
       }



       public void setOriginalAmount(float originalAmount) {
              this.originalAmount = originalAmount;
       }



       public float getDiscountAmount() {
              return discountAmount;
       }



       public void setDiscountAmount(float discountAmount) {
              this.discountAmount = discountAmount;
       }



       public float getFinalAmount() {
              return finalAmount;
       }



       public void setFinalAmount(float finalAmount) {
              this.finalAmount = finalAmount;
       }



       public String getStatus() {
              return status;
       }



       public void setStatus(String status) {
              this.status = status;
       }
}
