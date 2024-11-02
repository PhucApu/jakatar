package com.bus_station_ticket.project.ProjectDTO;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class AccountDTO {
       @NotBlank(message = "")
       private String userName;
       private String passWord;
       private String email;
       private String phoneNumber;
       private String role;
       private Boolean isBlock;
       private Boolean isDelete;
       private List<Long> listFeedbackEntities_Id;
       private List<Long> listTicketEntities_Id;

       public AccountDTO() {
       }

       public AccountDTO(String userName, String passWord, String email, String phoneNumber, String role,
                     Boolean isBlock, Boolean isDelete, List<Long> listFeedbackEntities_Id,
                     List<Long> listTicketEntities_Id) {
              this.userName = userName;
              this.passWord = passWord;
              this.email = email;
              this.phoneNumber = phoneNumber;
              this.role = role;
              this.isBlock = isBlock;
              this.isDelete = isDelete;
              this.listFeedbackEntities_Id = listFeedbackEntities_Id;
              this.listTicketEntities_Id = listTicketEntities_Id;
       }

       public String getUserName() {
              return userName;
       }

       public void setUserName(String userName) {
              this.userName = userName;
       }

       public String getPassWord() {
              return passWord;
       }

       public void setPassWord(String passWord) {
              this.passWord = passWord;
       }

       public String getEmail() {
              return email;
       }

       public void setEmail(String email) {
              this.email = email;
       }

       public String getPhoneNumber() {
              return phoneNumber;
       }

       public void setPhoneNumber(String phoneNumber) {
              this.phoneNumber = phoneNumber;
       }

       public String getRole() {
              return role;
       }

       public void setRole(String role) {
              this.role = role;
       }

       public Boolean getIsBlock() {
              return isBlock;
       }

       public void setIsBlock(Boolean isBlock) {
              this.isBlock = isBlock;
       }

       public List<Long> getListFeedbackEntities_Id() {
              return listFeedbackEntities_Id;
       }

       public void setListFeedbackEntities_Id(List<Long> listFeedbackEntities_Id) {
              this.listFeedbackEntities_Id = listFeedbackEntities_Id;
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



