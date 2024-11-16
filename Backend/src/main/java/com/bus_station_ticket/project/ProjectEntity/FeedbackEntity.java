package com.bus_station_ticket.project.ProjectEntity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
       @Column(name = "feedback_id")
       private Long feedbackId;

       @ManyToOne
       @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
       private AccountEntity accountEntity;

       @ManyToOne
       @JoinColumn(name = "ticket_id", referencedColumnName = "ticket_id", nullable = false)
       private TicketEntity ticketEntity;

       @Column(name = "content", columnDefinition = "TEXT")
       private String content;

       @Column(name = "rating")
       private int rating;

       @Column(name = "date_comment", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime dateComment;

       @Column(name = "is_delete", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
       private Boolean isDelete;

       public FeedbackEntity() {
       }

       public FeedbackEntity(AccountEntity accountEnitty, TicketEntity ticketEntity, String content, int rating,
                     LocalDateTime dateComment, Boolean isDelete) {
              this.accountEntity = accountEnitty;
              this.ticketEntity = ticketEntity;
              this.content = content;
              this.rating = rating;
              this.dateComment = dateComment;
              this.isDelete = isDelete;
       }

       public Long getFeedbackId() {
              return feedbackId;
       }

       public void setFeedbackId(Long feedbackId) {
              this.feedbackId = feedbackId;
       }

       public TicketEntity getTicketEntity() {
              return ticketEntity;
       }

       public void setTicketEntity(TicketEntity ticketEntity) {
              this.ticketEntity = ticketEntity;
       }

       public String getContent() {
              return content;
       }

       public void setContent(String content) {
              this.content = content;
       }

       public int getRating() {
              return rating;
       }

       public void setRating(int rating) {
              this.rating = rating;
       }

       public LocalDateTime getDateComment() {
              return dateComment;
       }

       public void setDateComment(LocalDateTime dateComment) {
              this.dateComment = dateComment;
       }

       public Boolean getIsDelete() {
              return isDelete;
       }

       public void setIsDelete(Boolean isDelete) {
              this.isDelete = isDelete;
       }

       @Override
       public String toString() {
              return "FeedbackEntity [feedbackId=" + feedbackId + ", accountEnitty=" + accountEntity + ", ticketEntity="
                            + ticketEntity + ", content=" + content + ", rating=" + rating + ", dateComment="
                            + dateComment + ", isDelete=" + isDelete + "]";
       }

       public AccountEntity getAccountEntity() {
              return accountEntity;
       }

       public void setAccountEntity(AccountEntity accountEntity) {
              this.accountEntity = accountEntity;
       }

}
