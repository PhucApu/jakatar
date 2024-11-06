package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PaymentDTO {

       @NotBlank(message = "paymentId cannot be blank")
       private Long paymentId;

       @NotNull(message = "Payment time cannot be null")
       private LocalDateTime paymentTime;

       @NotNull(message = "Original amount cannot be null")
       @Min(value = 0, message = "Original amount must be greater than or equal to 0")
       private float originalAmount;

       @NotNull(message = "Discount amount cannot be null")
       @Min(value = 0, message = "Discount amount must be greater than or equal to 0")
       private float discountAmount;

       @NotNull(message = "Final amount cannot be null")
       @Min(value = 0, message = "Final amount must be greater than or equal to 0")
       private float finalAmount;

       @NotBlank(message = "Status cannot be blank")
       @Pattern(regexp = "^(success|pending|failure)$", message = "There are only 3 status values: success, pending and failure")
       private String status;

       @NotBlank(message = "Payment method cannot be blank")
       @Size(max = 50, message = "Payment method cannot exceed 50 characters")
       private String paymentMethod;

       @NotNull(message = "List of ticket IDs cannot be null")
       private List<Long> listTicketEntities_Id;

       @NotNull(message = "Delete status cannot be null")
       private Boolean isDelete;

       public PaymentDTO() {
       }

       public PaymentDTO(Long paymentId, LocalDateTime paymentTime, float originalAmount, float discountAmount,
       float finalAmount, String status, String paymentMethod, List<Long> listTicketEntities_Id,
                     Boolean isDelete) {
              this.paymentId = paymentId;
              this.paymentTime = paymentTime;
              this.originalAmount = originalAmount;
              this.discountAmount = discountAmount;
              this.finalAmount = finalAmount;
              this.status = status;
              this.paymentMethod = paymentMethod;
              this.listTicketEntities_Id = listTicketEntities_Id;
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

       public List<Long> getListTicketEntities_Id() {
              return listTicketEntities_Id;
       }

       public void setListTicketEntities_Id(List<Long> listTicketEntities_Id) {
              this.listTicketEntities_Id = listTicketEntities_Id;
       }

       public Boolean getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(Boolean isDelete) {
              this.isDelete = isDelete;
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
