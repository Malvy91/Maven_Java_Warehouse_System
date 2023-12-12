package pl.wsb.Maven_Warehouse_System;
import java.util.HashMap;
import java.util.Map;

public class DataContainer {
    Map<String, Map<SupportedMetalType, Double>> clientsMap = new HashMap<>();
    // declaration of clients map, that contains client objects
    Map<String, Client> clients = new HashMap<>();
}
