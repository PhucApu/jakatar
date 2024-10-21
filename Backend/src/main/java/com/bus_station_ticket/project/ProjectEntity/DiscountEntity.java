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
@Table(name = "discount", indexes = @Index(columnList = "discount_id"))
public class DiscountEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "discount_id")
       private Long discountId;

       @Column(name = "discount_percentage", columnDefinition = "FLOAT", length = 5)
       private float discountPercentage;

       @Column(name = "valid_from", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime validFrom;

       @Column(name = "valid_until", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime validUntil;

       @Column(name = "amount", nullable = false)
       private int amount;

       @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean isDelete;

       @OneToMany(mappedBy = "discountEntity", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;

       

       public DiscountEntity() {
       }

       public DiscountEntity(float discountPercentage, LocalDateTime validFrom, LocalDateTime validUntil, int amount,
                     Boolean isDelete, List<TicketEntity> listTicketEntities) {
              this.discountPercentage = discountPercentage;
              this.validFrom = validFrom;
              this.validUntil = validUntil;
              this.amount = amount;
              this.isDelete = isDelete;
              this.listTicketEntities = listTicketEntities;
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

       public List<TicketEntity> getListTicketEntities() {
              return listTicketEntities;
       }

       public void setListTicketEntities(List<TicketEntity> listTicketEntities) {
              this.listTicketEntities = listTicketEntities;
       }

       @Override
       public String toString() {
              return "DiscountEntity [discountId=" + discountId + ", discountPercentage=" + discountPercentage
                            + ", validFrom=" + validFrom + ", validUntil=" + validUntil + ", amount=" + amount
                            + ", isDelete=" + isDelete + ", listTicketEntities=" + listTicketEntities + "]";
       }

       
}
