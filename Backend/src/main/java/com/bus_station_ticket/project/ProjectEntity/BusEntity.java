package com.bus_station_ticket.project.ProjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "bus", indexes = @Index(columnList = "bus_id"))
public class BusEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "bus_id", columnDefinition = "VARCHAR(20)")
       private String busId;

       @Column(name = "bus_number", nullable = false, columnDefinition = "VARCHAR(30)")
       private String busNumber;

       @Column(name = "capacity", nullable = false)
       private int capacity;

       @Column(name = "brand", nullable = false, columnDefinition = "VARCHAR(30)")
       private String brand;

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;

       @ManyToMany(mappedBy = "listBusEntity", fetch = FetchType.LAZY)
       private List<EmployeeEntity> listEmployeeEntities;


}
