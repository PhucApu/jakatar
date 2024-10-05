package com.bus_station_ticket.project.ProjectEntity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "account", indexes = @Index(columnList = "username")) // name table is "account", create index on column
                                                                    // "username"
public class AccountEnitty {

       @Id // primary key
       @Column(name = "username", columnDefinition = "VARCHAR(20)") // column: username, type: text
       private String userName;

       @Column(name = "password", nullable = false, columnDefinition = "TEXT") // column: passwprd, type: text, null: no
       private String passWord;

       @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(30)") // column: email, type: text, null:
                                                                                    // no
       private String email;

       @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(11)") // column: phone, type: nvarchar,
                                                                                    // null: no
       private String phoneNumber;

       @Column(name = "ROLE", nullable = false, columnDefinition = "ENUM('USER','ADMIN') DEFAULT 'USER'") // column:
                                                                                                          // ROLE, type:
                                                                                                          // nvarchar
                                                                                                          // (USER,ADMIN),
                                                                                                          // null: no
       private String role;

       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;

       @OneToMany(mappedBy = "accountEnitty", fetch = FetchType.LAZY)
       private List<FeedbackEntity> listFeedbackEntities;

       @OneToMany(mappedBy = "accountEnitty", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;


       // Contructor no argument
       public AccountEnitty() {
       }

       // Contructor full arguments

       public AccountEnitty(String userName, String passWord, String email, String phoneNumber, String role,
                     ChoiceEnum isDelete, List<FeedbackEntity> feedbackEntities) {
              this.userName = userName;
              this.passWord = passWord;
              this.email = email;
              this.phoneNumber = phoneNumber;
              this.role = role;
              this.isDelete = isDelete;
              this.listFeedbackEntities = feedbackEntities;
       }

       // GETTER AND SETTER
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

       public List<FeedbackEntity> getFeedbackEntities() {
              return listFeedbackEntities;
       }

       public void setFeedbackEntities(List<FeedbackEntity> feedbackEntities) {
              this.listFeedbackEntities = feedbackEntities;
       }

       public ChoiceEnum getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(ChoiceEnum isDelete) {
              this.isDelete = isDelete;
       }

       // overide toString()
       @Override
       public String toString() {
              return "AccountEnitty [userName=" + userName + ", passWord=" + passWord + ", email=" + email
                            + ", phoneNumber=" + phoneNumber + ", role=" + role + "]";
       }
}
