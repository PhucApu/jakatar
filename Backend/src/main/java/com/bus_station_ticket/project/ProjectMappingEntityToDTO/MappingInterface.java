package com.bus_station_ticket.project.ProjectMappingEntityToDTO;


// E --> Entity
// D --> DTO
public interface MappingInterface<E,D> {
       public D toDTO(E entity);
}
