package pl.wsb.Maven_Warehouse_System;

import java.time.LocalDate;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* pl.wsb.Maven_Warehouse_System.WarehouseHandlingSystem implements pl.wsb.Maven_Warehouse_System.Clients and pl.wsb.Maven_Warehouse_System.Warehouse interfaces, what means that
pl.wsb.Maven_Warehouse_System.WarehouseHandlingSystem contains the methods implementation for both interfaces.
 */
public class WarehouseHandlingSystem implements Clients, Warehouse{
    private final Client Client = new Client();
    private final VerifyCorrectness verifyCorrectness = new VerifyCorrectness();
    private final ClientIdGenerator clientIdGenerator = new ClientIdGenerator();
    // declaration of variable, where we will deliver creation date
    public LocalDate creationDate;
    WarehouseHandlingSystem(){
    }
    // pl.wsb.Maven_Warehouse_System.Clients interface implementation
    @Override
    public String createNewClient(String firstName, String lastName){
        try {
            verifyCorrectness.assertString("name", firstName);
            try {
                verifyCorrectness.assertString("surname", lastName);
                int freeMapIndex = Client.clients.size();
                String clientId = clientIdGenerator.generateClientId(firstName, lastName, freeMapIndex);
                creationDate = LocalDate.now();

                Client clientObject = Client.createClientObject(firstName, lastName, clientId, creationDate);
                Client.clients.put(clientId, clientObject);
                return clientId;
            } catch (NameNotFoundException ignored){
                return "Last name is unknown.";
            }

        } catch (NameNotFoundException ignored){
            return "First name is unknown.";
        }
    }
    @Override
    public String activatePremiumAccount(String clientId) {
        try {
            verifyCorrectness.verifyClientInMapById(clientId, Client.clients);
            if (Client.getPremium(clientId)) {
                System.out.println("Your client " + clientId + " has premium status.");
                return "Client has premium status!";
            } else {
                Client.setIsPremium(clientId, true);
            }
            return clientId;
        } catch (ClientNotFoundException ignored){
        return "Client not found!";
    }
    }
    @Override
    public String getClientFullName(String clientId) {
        try {
            verifyCorrectness.verifyClientInMapById(clientId, Client.clients);
            String clientFirstName = Client.getFirstName(clientId);
            String clientLastName = Client.getLastName(clientId);
            String clientFirstAndLastName = clientFirstName + " " + clientLastName;
            System.out.println("Clients name and surname: " + clientId + ": " + clientFirstAndLastName);
            return clientFirstAndLastName;
        } catch (ClientNotFoundException ignored) {
            return "Client not found!";
        }
    }
    @Override
    public LocalDate getClientCreationDate(String clientId) {
        try {
            verifyCorrectness.verifyClientInMapById(clientId, Client.clients);
            return Client.getCreationDate(clientId);
        } catch (ClientNotFoundException ignored) {
            return LocalDate.MIN;
        }
    }
    @Override
    public boolean isPremiumClient(String clientId) {
        try {
            verifyCorrectness.verifyClientInMapById(clientId, Client.clients);
            boolean isPremium = Client.getPremium(clientId);
            if (isPremium) {
                System.out.println("Client " + clientId + " has premium status.");
            } else {
                System.out.println("Client " + clientId + " has no premium status, yet. Please, suggest him/her available packages.");
            }
            return isPremium;
        } catch (ClientNotFoundException ignored){
            return false;
        }
    }
    @Override
    public int getNumberOfClients(){
        return Client.clients.size();
    }
    @Override
    public int getNumberOfPremiumClients(){
        int premiumClients= 0;
        for (String i : Client.clients.keySet()) {
            if (Client.getPremium(i)) {
                premiumClients++;
            }
        }
        System.out.println("There are " + premiumClients + " clients in database.");
        return premiumClients;
        }

    // pl.wsb.Maven_Warehouse_System.Warehouse implementation
    public void addMetalIngot(String clientId, SupportedMetalType metalType, double mass){
        Map<SupportedMetalType, Double> metalTypesToMassMap;
        double totalMass;
        try {
            verifyCorrectness.verifyClientInMapById(clientId, Client.clients);
            try {
                verifyCorrectness.verifyWarehouseCapacity(mass);
                try {
                    verifyCorrectness.verifyMetalCorrectness(metalType);

                    metalTypesToMassMap = getMetalTypesToMassStoredByClient(clientId);
                    if (metalTypesToMassMap == null) {
                        Map<SupportedMetalType, Double> newMetalTypesToMassMap = new HashMap<>();
                        newMetalTypesToMassMap.put(metalType, mass);
                        verifyCorrectness.clientsMap.put(clientId, newMetalTypesToMassMap);
                        System.out.println(Client.clients);
                    } else {
                        if (metalTypesToMassMap.containsKey(metalType)) {
                            totalMass = metalTypesToMassMap.get(metalType) + mass;
                            metalTypesToMassMap.replace(metalType, totalMass);
                            verifyCorrectness.clientsMap.replace(clientId, metalTypesToMassMap);
                        } else {
                            metalTypesToMassMap.put(metalType, mass);

                        }
                    }
                } catch (ProhibitedMetalTypeException ignored){
                }
            } catch (FullWarehouseException ignored){
            }
        } catch (ClientNotFoundException ignored){
        }
    }

    public Map<SupportedMetalType, Double> getMetalTypesToMassStoredByClient(String clientId) {
        if (verifyCorrectness.clientsMap == null) {
            Map<String, Map<SupportedMetalType, Double>> clientsMap = new HashMap<>();
            Map<SupportedMetalType, Double> newMetalTypesToMassMap = new HashMap<>();
            clientsMap.put(clientId, newMetalTypesToMassMap);
        }
            return verifyCorrectness.clientsMap.get(clientId);
    }
    public double getTotalVolumeOccupiedByClient(String clientId){
        try {
            verifyCorrectness.verifyClientInWarehouseMapById(clientId, verifyCorrectness.clientsMap);
            double totalVolumeOccupiedByClient = 0.0;
            double VolumeOccupiedBySingleMetal;
            double density = 0.0;
            for (SupportedMetalType i : getMetalTypesToMassStoredByClient(clientId).keySet()) {
                // d = m/V
                density = switch (i) {
                    case COPPER -> SupportedMetalType.COPPER.getDensity();
                    case TIN -> SupportedMetalType.TIN.getDensity();
                    case IRON -> SupportedMetalType.IRON.getDensity();
                    case LEAD -> SupportedMetalType.LEAD.getDensity();
                    case SILVER -> SupportedMetalType.SILVER.getDensity();
                    case TUNGSTEN -> SupportedMetalType.TUNGSTEN.getDensity();
                    case GOLD -> SupportedMetalType.GOLD.getDensity();
                    case PLATINUM -> SupportedMetalType.PLATINUM.getDensity();
                    default -> throw new ProhibitedMetalTypeException("Our warehouse does not support that metal type.");
                };
                if (density == 0.0){
                    return 0;
                }
                VolumeOccupiedBySingleMetal = getMetalTypesToMassStoredByClient(clientId).get(i) / density;
                totalVolumeOccupiedByClient += VolumeOccupiedBySingleMetal;
            }
            return totalVolumeOccupiedByClient;
        } catch (ClientNotFoundException ignored){
            return 0;
        }
    }

    public List<SupportedMetalType> getStoredMetalTypesByClient(String clientId){
        try {
            verifyCorrectness.verifyClientInWarehouseMapById(clientId, verifyCorrectness.clientsMap);
            return new ArrayList<>(verifyCorrectness.clientsMap.get(clientId).keySet());
        } catch (ClientNotFoundException ignored){
            return new ArrayList<>();
        }
    }

}
