package pl.wsb.Maven_Warehouse_System;

import java.time.LocalDate;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Float.NaN;

/* pl.wsb.Maven_Warehouse_System.WarehouseHandlingSystem implements pl.wsb.Maven_Warehouse_System.Clients and pl.wsb.Maven_Warehouse_System.Warehouse interfaces, what means that
pl.wsb.Maven_Warehouse_System.WarehouseHandlingSystem contains the methods implementation for both interfaces.
 */
public class WarehouseHandlingSystem implements Clients, Warehouse{
    private final CorrectnessVerification correctnessVerification = new CorrectnessVerification();
    private final ClientIdGenerator clientIdGenerator = new ClientIdGenerator();
    private final DataContainer dataContainer = new DataContainer();

    // declaration of variable, where we will deliver creation date
    public LocalDate creationDate;
    // Clients interface implementation
    @Override
    public String createNewClient(String firstName, String lastName){
        try {
            correctnessVerification.assertString("name", firstName);
            try {
                correctnessVerification.assertString("surname", lastName);
                int freeMapIndex = dataContainer.clients.size();
                String clientId = clientIdGenerator.generateClientId(firstName, lastName, freeMapIndex);
                creationDate = LocalDate.now();

                Client clientObject = new Client(firstName, lastName, clientId, creationDate);
                dataContainer.clients.put(clientId, clientObject);
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
            correctnessVerification.verifyClientInMapById(clientId, dataContainer.clients);
            if (dataContainer.clients.get(clientId).isPremium) {
                System.out.println("Your client " + clientId + " has premium status.");
                return "Client has premium status!";
            } else {
                dataContainer.clients.get(clientId).isPremium = true;
            }
            return clientId;
        } catch (ClientNotFoundException ignored){
        return "Client not found!";
    }
    }
    @Override
    public String getClientFullName(String clientId) {
        try {
            correctnessVerification.verifyClientInMapById(clientId, dataContainer.clients);
            Client client = dataContainer.clients.get(clientId);
            String clientFirstAndLastName = client.firstName + " " + client.lastName;
            System.out.println("Clients name and surname: " + clientId + ": " + clientFirstAndLastName);
            return clientFirstAndLastName;
        } catch (ClientNotFoundException ignored) {
            return "Client not found!";
        }
    }
    @Override
    public LocalDate getClientCreationDate(String clientId) {
        try {
            correctnessVerification.verifyClientInMapById(clientId, dataContainer.clients);
            return dataContainer.clients.get(clientId).creationDate;
        } catch (ClientNotFoundException ignored) {
            return null;
        }
    }
    @Override
    public boolean isPremiumClient(String clientId) {
        try {
            correctnessVerification.verifyClientInMapById(clientId, dataContainer.clients);
            boolean isPremium = dataContainer.clients.get(clientId).isPremium;
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
        return dataContainer.clients.size();
    }
    @Override
    public int getNumberOfPremiumClients(){
        int premiumClients= 0;
        for (String i : dataContainer.clients.keySet()) {
            if (dataContainer.clients.get(i).isPremium) {
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
            correctnessVerification.verifyClientInMapById(clientId, dataContainer.clients);
            try {
                correctnessVerification.verifyWarehouseCapacity(mass);
                try {
                    correctnessVerification.verifyMetalCorrectness(metalType);

                    metalTypesToMassMap = getMetalTypesToMassStoredByClient(clientId);
                    if (metalTypesToMassMap == null) {
                        Map<SupportedMetalType, Double> newMetalTypesToMassMap = new HashMap<>();
                        newMetalTypesToMassMap.put(metalType, mass);
                        dataContainer.clientsMap.put(clientId, newMetalTypesToMassMap);
                        System.out.println(dataContainer.clients);
                    } else {
                        if (metalTypesToMassMap.containsKey(metalType)) {
                            totalMass = metalTypesToMassMap.get(metalType) + mass;
                            metalTypesToMassMap.replace(metalType, totalMass);
                            dataContainer.clientsMap.replace(clientId, metalTypesToMassMap);
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
        if (dataContainer.clientsMap == null) {
            Map<String, Map<SupportedMetalType, Double>> clientsMap = new HashMap<>();
            Map<SupportedMetalType, Double> newMetalTypesToMassMap = new HashMap<>();
            clientsMap.put(clientId, newMetalTypesToMassMap);
        }
        assert dataContainer.clientsMap != null;
        return dataContainer.clientsMap.get(clientId);
    }
    public double getTotalVolumeOccupiedByClient(String clientId){
        try {
            correctnessVerification.verifyClientInWarehouseMapById(clientId, dataContainer.clientsMap);
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
            return NaN;
        }
    }

    public List<SupportedMetalType> getStoredMetalTypesByClient(String clientId){
        try {
            correctnessVerification.verifyClientInWarehouseMapById(clientId, dataContainer.clientsMap);
            return new ArrayList<>(dataContainer.clientsMap.get(clientId).keySet());
        } catch (ClientNotFoundException ignored){
            return null;
        }
    }

}
