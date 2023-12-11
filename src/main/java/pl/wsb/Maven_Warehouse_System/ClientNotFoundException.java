package pl.wsb.Maven_Warehouse_System;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(){
    }
    public  ClientNotFoundException(String comment){
        System.out.println(comment);
    }
}
