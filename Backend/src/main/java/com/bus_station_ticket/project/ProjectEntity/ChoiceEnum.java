package com.bus_station_ticket.project.ProjectEntity;

public enum ChoiceEnum {
       YES(1) , NO(0);

       private int choice;
       
       private ChoiceEnum(int choice ){
              this.choice = choice;
       }

       public int getChoiceValueInt(){
              return this.choice;
       }
}
