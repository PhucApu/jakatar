package com.bus_station_ticket.project.ProjectDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeedbackDTO {

       @NotNull(message = "Feedback ID cannot be null")
       private Long feedbackId;

       @NotBlank(message = "Account username cannot be blank")
       @Size(max = 20, message = "Account username must not exceed 20 characters")
       private String accountEnity_userName;

       @NotNull(message = "Ticket entity ID cannot be null")
       private Long ticketEntity_Id;

       @NotBlank(message = "Content cannot be blank")
       @Size(max = 500, message = "Content must not exceed 500 characters")
       private String content;

       @Min(value = 1, message = "Rating must be at least 1")
       @Max(value = 5, message = "Rating must not exceed 5")
       private int rating;

       @NotNull(message = "Date of comment cannot be null")
       private LocalDateTime dateComment;

       @NotNull(message = "Delete status cannot be null")
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
