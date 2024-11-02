package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;
import java.util.List;

public class DiscountDTO {

       private Long discountId;
       private float discountPercentage;
       private LocalDateTime validFrom;
       private LocalDateTime validUntil;
       private int amount;
       private Boolean isDelete;
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
