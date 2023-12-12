package pl.wsb.Maven_Warehouse_System;

import java.time.LocalDate;


public class Client {
    String firstName;
    String lastName;
    String clientId;
    boolean isPremium;
    LocalDate creationDate;
    // client's objects' constructor
    Client(){
    }
    public Client(String firstName, String lastName, String clientId, LocalDate creationDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientId = clientId;
        this.isPremium = false;
        this.creationDate = creationDate;
    }
}
