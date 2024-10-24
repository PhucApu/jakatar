package com.bus_station_ticket.project.ProjectMappingEntityToDtoSevice;

// E --> Entity
// D --> DTO
public interface MappingInterface<E, D> {


       public D toDTO(E entity);

       public E toEntity(D dto);
}
