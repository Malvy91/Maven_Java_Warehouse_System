package pl.wsb.Maven_Warehouse_System;

public class NameNotFoundException extends RuntimeException{
    public NameNotFoundException() {
    }
    public NameNotFoundException(String comment) {
        System.out.println(comment);
    }
}
