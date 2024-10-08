package com.bus_station_ticket.project.ProjectEntity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
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

       @OneToMany(mappedBy = "accountEnitty", fetch = FetchType.LAZY)
       private List<FeedbackEntity> listFeedbackEntities;

       @OneToMany(mappedBy = "accountEnitty", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;


       
}
