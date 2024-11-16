package com.bus_station_ticket.project.ProjectSecurity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bus_station_ticket.project.ProjectEntity.AccountEntity;

public class UserDetailsConfig implements UserDetails {

       private AccountEntity accountEntity;

       public UserDetailsConfig(AccountEntity accountEntity) {
              this.accountEntity = accountEntity;
       }

       // Trả lại role ủa account
       @Override
       public Collection<? extends GrantedAuthority> getAuthorities() {
              return List.of(new SimpleGrantedAuthority(this.accountEntity.getRole()));
       }

       @Override
       public String getPassword() {
              return this.accountEntity.getPassWord();
       }

       @Override
       public String getUsername() {
              return this.accountEntity.getUserName();
       }

       // Kiểm tra tài khoản có được mở khóa không
       @Override
       public boolean isAccountNonLocked() {
              if(this.accountEntity.getIsBlock() == false){
                     return true;
              }
              return false;
       }

}
