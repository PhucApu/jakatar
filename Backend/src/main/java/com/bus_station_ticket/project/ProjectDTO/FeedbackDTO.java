package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;

public class FeedbackDTO {

       private Long feedbackId;
       private String accountEnity_userName;
       private Long ticketEntity_Id;
       private String content;
       private int rating;
       private LocalDateTime dateComment;
       private Boolean isDelete;

       public FeedbackDTO() {
       }

       public FeedbackDTO(Long feedbackId, String accountEnity_userName, Long ticketEntity_Id, String content,
                     int rating, LocalDateTime dateComment, Boolean isDelete) {
              this.feedbackId = feedbackId;
              this.accountEnity_userName = accountEnity_userName;
              this.ticketEntity_Id = ticketEntity_Id;
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

       public String getAccountEnity_userName() {
              return accountEnity_userName;
       }

       public void setAccountEnity_userName(String accountEnity_userName) {
              this.accountEnity_userName = accountEnity_userName;
       }

       public Long getTicketEntity_Id() {
              return ticketEntity_Id;
       }

       public void setTicketEntity_Id(Long ticketEntity_Id) {
              this.ticketEntity_Id = ticketEntity_Id;
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

}
