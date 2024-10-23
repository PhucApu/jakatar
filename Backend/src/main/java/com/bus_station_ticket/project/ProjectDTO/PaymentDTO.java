package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentDTO {

       private Long paymentId;
       private LocalDateTime paymentTime;
       private String paymentMethod;
       private List<Long> listTicketEntities_Id;
       private Boolean isDelete;

       public PaymentDTO() {
       }

       public PaymentDTO(Long paymentId, LocalDateTime paymentTime, String paymentMethod,
                     List<Long> listTicketEntities_Id, Boolean isDelete) {
              this.paymentId = paymentId;
              this.paymentTime = paymentTime;
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

}
