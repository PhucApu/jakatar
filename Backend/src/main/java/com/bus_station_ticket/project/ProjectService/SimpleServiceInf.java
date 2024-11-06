package com.bus_station_ticket.project.ProjectService;

import java.util.List;

public interface SimpleServiceInf<ENTITY,DTO,ID> {
       
       // Entity
       public ENTITY getById(ID id);
       public List<ENTITY> getAll();
       public Boolean save (ENTITY entityObj);
       public Boolean update (ENTITY entityObj);
       public Boolean delete (ID id);

       // dto
       public DTO getById_toDTO(ID id);
       public List<DTO> getAll_toDTO();
       public Boolean save_toDTO (DTO dtoObj);
       public Boolean update_toDTO (DTO dtoObj);


}
