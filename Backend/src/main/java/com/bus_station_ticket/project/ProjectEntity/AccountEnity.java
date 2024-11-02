package com.bus_station_ticket.project.ProjectEntity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "account", indexes = @Index(columnList = "username")) // name table is "account", create index on column
                                                                    // "username"
public class AccountEnity {

       @Id // primary key
       @Column(name = "username", columnDefinition = "VARCHAR(20)") // column: username, type: text
       private String userName;

       @Column(name = "password", nullable = false, columnDefinition = "TEXT") // column: passwprd, type: text, null: no
       private String passWord;

       @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(30)") // column: email, type: text, null:
                                                                                    // no
       private String email;

       @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(15)") // column: phone, type: nvarchar,
                                                                                    // null: no
       private String phoneNumber;

       @Column(name = "ROLE", nullable = false, columnDefinition = "ENUM('USER','ADMIN') DEFAULT 'USER'") // column:
                                                                                                          // ROLE, type:
                                                                                                          // nvarchar
                                                                                                          // (USER,ADMIN),
                                                                                                          // null: no
       private String role;

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;


       @Enumerated(EnumType.STRING)
       @Column(name = "is_block", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isBlock;


       @OneToMany(mappedBy = "accountEnitty", fetch = FetchType.LAZY)
       private List<FeedbackEntity> listFeedbackEntities;

       @OneToMany(mappedBy = "accountEnitty", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;


       


       public AccountEnity() {}

       public AccountEnity(String userName, String passWord, String email, String phoneNumber, String role,
                     ChoiceEnum isDelete, ChoiceEnum isBlock, List<FeedbackEntity> listFeedbackEntities,
                     List<TicketEntity> listTicketEntities) {
              this.userName = userName;
              this.passWord = passWord;
              this.email = email;
              this.phoneNumber = phoneNumber;
              this.role = role;
              this.isDelete = isDelete;
              this.isBlock = isBlock;
              this.listFeedbackEntities = listFeedbackEntities;
              this.listTicketEntities = listTicketEntities;
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

       public ChoiceEnum getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(ChoiceEnum isDelete) {
              this.isDelete = isDelete;
       }

       public ChoiceEnum getIsBlock() {
              return isBlock;
       }

       public void setIsBlock(ChoiceEnum isBlock) {
              this.isBlock = isBlock;
       }

       public List<FeedbackEntity> getListFeedbackEntities() {
              return listFeedbackEntities;
       }

       public void setListFeedbackEntities(List<FeedbackEntity> listFeedbackEntities) {
              this.listFeedbackEntities = listFeedbackEntities;
       }

       public List<TicketEntity> getListTicketEntities() {
              return listTicketEntities;
       }

       public void setListTicketEntities(List<TicketEntity> listTicketEntities) {
              this.listTicketEntities = listTicketEntities;
       }

       @Override
       public String toString() {
              return "AccountEnity [userName=" + userName + ", passWord=" + passWord + ", email=" + email
                            + ", phoneNumber=" + phoneNumber + ", role=" + role + ", isDelete=" + isDelete
                            + ", isBlock=" + isBlock + ", listFeedbackEntities=" + listFeedbackEntities
                            + ", listTicketEntities=" + listTicketEntities + "]";
       }

       
       

}
