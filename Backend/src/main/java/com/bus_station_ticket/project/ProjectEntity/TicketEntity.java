package com.bus_station_ticket.project.ProjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ticket" , indexes = @Index(columnList = "ticket_id"))
public class TicketEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "ticket_id", columnDefinition = "TEXT")
       private String ticketId;

       @ManyToOne
       @JoinColumn(name = "username", referencedColumnName = "username", nullable = true, insertable = false, updatable = true)
       private AccountEnitty accountEnitty;

       @ManyToOne
       @JoinColumn(name = "bus_id", referencedColumnName = "bus_id", nullable = false, insertable = false, updatable = true)
       private BusEntity busEntity;

       @ManyToOne
       @JoinColumn(name = "routes_id", referencedColumnName = "routes_id", nullable = false, insertable = false, updatable = true)
       private BusRoutesEntity busRoutesEntity;

       @ManyToOne
       @JoinColumn(name = "payment_id", referencedColumnName = "payment_id", nullable = false, insertable = false, updatable = true)
       private PaymentEntity paymentEntity;

       @ManyToOne
       @JoinColumn(name = "discount_id", referencedColumnName = "discount_id", nullable = true, insertable = false, updatable = true)
       private DiscountEntity discountEntity;
       

       @Column(name = "seat_number", nullable = false, columnDefinition = "VARCHAR(10)")
       private String seatNumber;

       @Column(name = "departure_date", nullable = false, columnDefinition = "DATETIME")
       private LocalDate departureDate;

       @Column(name = "price", nullable = false, columnDefinition = "FLOAT", precision = 10, scale = 4 )
       private float price;

      
       @Column(name = "username", nullable = true, columnDefinition = "VARCHAR(20)")
       private String userName;

       @Column(name = "phone", nullable = true, columnDefinition = "VARCHAR(11)")
       private String phoneNumber;

       @Column(name = "email", nullable = true, columnDefinition = "VARCHAR(30)")
       private String email;  

       @OneToMany(mappedBy = "ticketEntity", orphanRemoval = true)
       private List<FeedbackEntity> listFeedbackEntities;
}
