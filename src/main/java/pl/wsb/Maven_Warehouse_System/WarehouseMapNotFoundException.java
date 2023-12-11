package pl.wsb.Maven_Warehouse_System;

public class WarehouseMapNotFoundException extends Throwable {
    public WarehouseMapNotFoundException() {
    }
    public WarehouseMapNotFoundException(String comment) {
        System.out.println(comment);
    }
}