package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DiscountDTO {

       @NotNull(message = "Discount ID cannot be null")
       private Long discountId;

       @Min(value = 0, message = "Discount percentage must be 0 or greater")
       @Max(value = 100, message = "Discount percentage must be 100 or less")
       private float discountPercentage;

       @NotNull(message = "Valid from date cannot be null")
       private LocalDateTime validFrom;

       @NotNull(message = "Valid until date cannot be null")
       private LocalDateTime validUntil;

       @Min(value = 1, message = "Amount must be at least 1")
       private int amount;

       @NotNull(message = "Delete status cannot be null")
       private Boolean isDelete;

       @NotNull(message = "Ticket entity IDs cannot be null")
       private List<Long> listTicketEntities_Id;

       public DiscountDTO() {
       }

       public DiscountDTO(Long discountId, float discountPercentage, LocalDateTime validFrom, LocalDateTime validUntil,
                     int amount, Boolean isDelete, List<Long> listTicketEntities_Id) {
              this.discountId = discountId;
              this.discountPercentage = discountPercentage;
              this.validFrom = validFrom;
              this.validUntil = validUntil;
              this.amount = amount;
              this.isDelete = isDelete;
              this.listTicketEntities_Id = listTicketEntities_Id;
       }

       public Long getDiscountId() {
              return discountId;
       }

       public void setDiscountId(Long discountId) {
              this.discountId = discountId;
       }

       public float getDiscountPercentage() {
              return discountPercentage;
       }

       public void setDiscountPercentage(float discountPercentage) {
              this.discountPercentage = discountPercentage;
       }

       public LocalDateTime getValidFrom() {
              return validFrom;
       }

       public void setValidFrom(LocalDateTime validFrom) {
              this.validFrom = validFrom;
       }

       public LocalDateTime getValidUntil() {
              return validUntil;
       }

       public void setValidUntil(LocalDateTime validUntil) {
              this.validUntil = validUntil;
       }

       public int getAmount() {
              return amount;
       }

       public void setAmount(int amount) {
              this.amount = amount;
       }

       public Boolean getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(Boolean isDelete) {
              this.isDelete = isDelete;
       }

       public List<Long> getListTicketEntities_Id() {
              return listTicketEntities_Id;
       }

       public void setListTicketEntities_Id(List<Long> listTicketEntities_Id) {
              this.listTicketEntities_Id = listTicketEntities_Id;
       }

}
