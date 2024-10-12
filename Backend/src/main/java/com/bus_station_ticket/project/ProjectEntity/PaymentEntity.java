package com.bus_station_ticket.project.ProjectEntity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "payment", indexes = @Index(columnList = "payment_id"))
public class PaymentEntity {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "payment_id" ,columnDefinition = "VARCHAR(20)")
       private String paymentId;

       @Column(name = "payment_time", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime paymentTime;

       @Column(name = "payment_method", nullable = false, columnDefinition = "VARCHAR(20)")
       private String paymentMethod;

       @OneToMany(mappedBy = "paymentEntity", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;
}
