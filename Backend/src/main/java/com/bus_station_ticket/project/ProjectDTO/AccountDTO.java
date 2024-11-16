package com.bus_station_ticket.project.ProjectDTO;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountDTO {

       @NotBlank(message = "Cannot be left blank !")
       @Size(max = 20, min = 5, message = "Account username must be at least 5 characters and at most 20 characters")
       private String userName;

       @NotBlank(message = "Cannot be left blank !")
       @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z0-9!@#$%^&*(),.?\":{}|<>]{5,20}$", message = "Must be 5-20 characters long, containing at least one digit, one uppercase letter, one lowercase letter, and one special character")
       private String passWord;

       @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(gmail|outlook)\\.com$", message = "You need to enter your gmail or outlook address")
       private String email;

       @Pattern(regexp = "^(03|05|07|08|09)[0-9]{8}$", message = "Your phone number is not valid")
       private String phoneNumber;

       @Pattern(regexp = "^(ROLE_ADMIN|ROLE_USER)", message = "value must be ROLE_ADMIN or ROLE_USER")
       private String role;

       @NotNull(message = "Block status cannot be null")
       private Boolean isBlock;

       @NotNull(message = "Delete status cannot be null")
       private Boolean isDelete;

       @NotNull(message = "Feedback entity IDs cannot be null")
       private List<Long> listFeedbackEntities_Id;

       @NotNull(message = "Ticket entity IDs cannot be null")
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
