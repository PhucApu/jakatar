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
@Table(name = "discount", indexes = @Index(columnList = "discount_id"))
public class DiscountEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "discount_id", nullable = false, columnDefinition = "VARCHAR(20)")
       private String discountId;

       @Column(name = "discount_percentage", columnDefinition = "FLOAT", length = 5)
       private float discountPercentage;

       @Column(name = "valid_from", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime validFrom;

       @Column(name = "valid_until", nullable = false, columnDefinition = "DATETIME")
       private LocalDateTime validUntil;

       @Column(name = "ammount", nullable = false)
       private int ammount;

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;

       @OneToMany(mappedBy = "discountEntity", fetch = FetchType.LAZY)
       private List<TicketEntity> listTicketEntities;
}
