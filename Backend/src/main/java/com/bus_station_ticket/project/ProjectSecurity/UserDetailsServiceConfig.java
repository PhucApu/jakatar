package com.bus_station_ticket.project.ProjectSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bus_station_ticket.project.ProjectEntity.AccountEntity;
import com.bus_station_ticket.project.ProjectRepository.AccountRepo;

public class UserDetailsServiceConfig implements UserDetailsService {

       private AccountRepo accountRepo;

       @Autowired
       public UserDetailsServiceConfig(AccountRepo accountRepo) {
              this.accountRepo = accountRepo;
       }

       @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

              // Lấy accountEntity dựa vào username
              AccountEntity accountEntity = this.accountRepo.findByUserName(username).orElse(null);

              if(accountEntity == null){
                     throw new UsernameNotFoundException("User not found");
              }
              
              return new UserDetailsConfig(accountEntity);
       }
}
