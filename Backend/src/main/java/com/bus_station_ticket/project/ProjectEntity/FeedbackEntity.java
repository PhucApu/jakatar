package com.bus_station_ticket.project.ProjectEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback", indexes = @Index(columnList = "feedback_id"))
public class FeedbackEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "feedback_id", columnDefinition = "TEXT")
       private String feedbackId;

       @ManyToOne
       @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
       private AccountEnity accountEnitty;

       @ManyToOne
       @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id", nullable = false )
       private TicketEntity ticketEntity;

       @Column(name = "content", columnDefinition = "TEXT")
       private String content;

       @Column(name = "rating")
       private int rating;

       @Column(name = "date-comment", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime dateComment ;

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;

}
