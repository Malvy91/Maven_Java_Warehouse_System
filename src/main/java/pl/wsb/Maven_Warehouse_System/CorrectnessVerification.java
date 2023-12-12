package pl.wsb.Maven_Warehouse_System;

import java.util.HashMap;
import java.util.Map;

public class CorrectnessVerification {
    private final DataContainer dataContainer = new DataContainer();
    // declaration of max size of warehouse
    double warehouseMaxSize = 10000; //m3
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
    double getTotalVolumeOccupiedByAllClients(Map<String, Map<SupportedMetalType, Double>> clientsMap){
            double totalVolumeOccupiedByAllClients = 0;
            for ( String i : clientsMap.keySet()) {
                for ( SupportedMetalType j : clientsMap.get(i).keySet()) {
                    totalVolumeOccupiedByAllClients += clientsMap.get(i).get(j);
                }
            }
            return totalVolumeOccupiedByAllClients;
    }
    public void verifyWarehouseCapacity(double mass) throws FullWarehouseException{
        double totalVolumeOccupiedByAllClients = getTotalVolumeOccupiedByAllClients(dataContainer.clientsMap);
        if (totalVolumeOccupiedByAllClients < warehouseMaxSize){
            if (mass > (warehouseMaxSize - totalVolumeOccupiedByAllClients)){
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
