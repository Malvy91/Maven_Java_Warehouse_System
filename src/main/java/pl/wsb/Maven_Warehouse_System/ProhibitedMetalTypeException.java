package pl.wsb.Maven_Warehouse_System;

public class ProhibitedMetalTypeException extends RuntimeException {
    public ProhibitedMetalTypeException(){
    }
    public ProhibitedMetalTypeException(String comment){
        System.out.println(comment);
    }
}
