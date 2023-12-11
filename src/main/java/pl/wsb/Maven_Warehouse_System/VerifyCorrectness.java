package pl.wsb.Maven_Warehouse_System;

import java.util.HashMap;
import java.util.Map;

public class VerifyCorrectness {
    // declaration of map clientsMap, that contains data about metals and its mass that is delivered by clients to warehouse
    Map<String, Map<SupportedMetalType, Double>> clientsMap = new HashMap<String, Map<SupportedMetalType, Double>>();
    // declaration of max size of warehouse
    double warehouseMaxSize = 10000; //m3
    VerifyCorrectness(){
    }
    public void assertString( String whatString, String string) throws NameNotFoundException{
        if (string.isEmpty()){
            throw new NameNotFoundException("Name is unknown. Please enter the " + whatString + ".");
        }
    }
    public void verifyClientInMapById(String clientId, Map<String, Client> map) throws ClientNotFoundException{
        if (!map.containsKey(clientId)){
            throw new ClientNotFoundException("Client not found in database!");
        }
    }
    public void verifyClientInWarehouseMapById(String clientId, Map<String, Map<SupportedMetalType, Double>> map) throws ClientNotFoundException{
        if (!map.containsKey(clientId)){
            throw new ClientNotFoundException("Client not found in WarehouseMap!");
        }
    }
    double getTotalVolumeOccupiedByAllClients(){
        double totalVolumeOccupiedByAllClients = 0;
        for ( String i : clientsMap.keySet()) {
            for ( SupportedMetalType j : clientsMap.get(i).keySet()) {
                totalVolumeOccupiedByAllClients += clientsMap.get(i).get(j);
            }
        }
        return totalVolumeOccupiedByAllClients;
    }
    public void verifyWarehouseCapacity(double mass) throws FullWarehouseException{
        if (getTotalVolumeOccupiedByAllClients() < warehouseMaxSize){
            if (mass > (warehouseMaxSize - getTotalVolumeOccupiedByAllClients())){
                throw new FullWarehouseException("Warehouse is full. We cannot accept the goods.");
            }
        }
    }
    public void verifyMetalCorrectness(SupportedMetalType metalType) throws ProhibitedMetalTypeException{
        switch (metalType){
            case COPPER, TIN, IRON, LEAD, SILVER, TUNGSTEN, GOLD, PLATINUM:
                break;
            default:
                throw new ProhibitedMetalTypeException("Our warehouse does not support that metal type.");
        }
    }
}
