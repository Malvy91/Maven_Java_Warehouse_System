package pl.wsb.Maven_Warehouse_System;

import java.time.LocalDate;


public class Client {
    private String firstName;
    private String lastName;
    private String clientId;
    private boolean isPremium;
    private LocalDate creationDate;
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
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getClientId(){
        return clientId;
    }
    public boolean getIsPremium(){
        return isPremium;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setIsPremium( boolean setPremiumStatus){
        this.isPremium = setPremiumStatus;
    }
}
