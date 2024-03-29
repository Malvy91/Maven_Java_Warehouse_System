package pl.wsb.Maven_Warehouse_System;

public class Main {
    public static void main(String[] args) {
        WarehouseHandlingSystem warehouseHandlingSystem = new WarehouseHandlingSystem();
        Clients clients = warehouseHandlingSystem;
        Warehouse warehouse= warehouseHandlingSystem;

        System.out.println("pl.wsb.Maven_Warehouse_System.Clients account created. They client Id: " + clients.createNewClient("Malwina", "Wajdzik"));
        System.out.println("pl.wsb.Maven_Warehouse_System.Clients account created. They client Id: " + clients.createNewClient("Krysia", "Tomczak"));
        System.out.println("pl.wsb.Maven_Warehouse_System.Clients account created. They client Id: " + clients.createNewClient("Aleksandra", "Wiktoria"));
        System.out.println("pl.wsb.Maven_Warehouse_System.Client " + clients.activatePremiumAccount("KT1") + "has a set up premium account");
        System.out.println("Has client KT1 premium account: " + clients.isPremiumClient("KT1"));
        System.out.println("Full name of client with id KT1 is: " + clients.getClientFullName("KT1"));
        System.out.println("pl.wsb.Maven_Warehouse_System.Client KT1 record was created on: " + clients.getClientCreationDate("KT1") );
        System.out.println("Has client KT1 premium account: " + clients.isPremiumClient("KT1"));
        System.out.println("Has client MW0 premium account: " + clients.isPremiumClient("MW0"));
        System.out.println("Number of clients in base: " + clients.getNumberOfClients());
        System.out.println("Number of premium clients in base: " + clients.getNumberOfPremiumClients());
        System.out.println("Number of premium clients in base: " + clients.getNumberOfPremiumClients());

        warehouse.addMetalIngot("KT1", SupportedMetalType.GOLD, 300.0);
        warehouse.addMetalIngot("KT1", SupportedMetalType.GOLD, 700.0);
        warehouse.addMetalIngot("MW0", SupportedMetalType.GOLD, 300.0);
        warehouse.addMetalIngot("MW0", SupportedMetalType.COPPER, 2000.0);
        System.out.println("Metal and its amount stored by KT1: " + warehouse.getMetalTypesToMassStoredByClient("KT1"));
        System.out.println("Metal and its amount stored by MW0: " + warehouse.getMetalTypesToMassStoredByClient("MW0"));
        System.out.println("Total occupied Volume by KT1 client: " + warehouse.getTotalVolumeOccupiedByClient("KT1") + " m3");
        System.out.println("Total occupied Volume by MW0 client: " + warehouse.getTotalVolumeOccupiedByClient("MW0") + " m3");
        System.out.println("Metal stored by MW0: " + warehouse.getStoredMetalTypesByClient("MW0"));
        System.out.println("Metal stored by KT1: " + warehouse.getStoredMetalTypesByClient("KT1"));

    }
}