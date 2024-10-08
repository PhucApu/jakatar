package com.bus_station_ticket.project.ProjectEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "employee", indexes = @Index(columnList = "driver_id"))
public class EmployeeEntity {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "driver_id", columnDefinition = "VARCHAR(20)")
       private String driverId;

       @Column(name = "id_driver", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDriver;

       @Column(name = "driver_name", nullable = false, columnDefinition = "VARCHAR(50)")
       private String driverName;

       @Column(name = "license_number", nullable = false, columnDefinition = "VARCHAR(50)")
       private String licenseNumber;

       @Column(name = "phone_number", nullable = false, columnDefinition = "VARCHAR(11)")
       private String phoneNumber;

       @Enumerated(EnumType.STRING)
       @Column(name = "is_delete", nullable = false, columnDefinition = "ENUM('YES','NO') DEFAULT 'NO'")
       private ChoiceEnum isDelete;

       @ManyToMany
       @JoinTable(
              name = "bus_employee", 
              joinColumns = @JoinColumn(name = "driver_id"), 
              inverseJoinColumns = @JoinColumn(name = "bus_id")
       )
       private List<BusEntity> listBusEntity;

       

}
