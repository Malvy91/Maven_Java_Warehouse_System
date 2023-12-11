package pl.wsb.Maven_Warehouse_System;

public class FullWarehouseException extends RuntimeException {
    public FullWarehouseException(){
    }
    public FullWarehouseException(String comment){
        System.out.println(comment);
        System.exit(0);
    }
}
