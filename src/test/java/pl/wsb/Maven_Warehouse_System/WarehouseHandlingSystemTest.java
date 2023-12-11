package pl.wsb.Maven_Warehouse_System;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.wsb.Maven_Warehouse_System.SupportedMetalType.*;

class WarehouseHandlingSystemTest {

    private final WarehouseHandlingSystem warehouseHandlingSystem = new WarehouseHandlingSystem();
    @BeforeEach
    void createClientsMap() {
        warehouseHandlingSystem.createNewClient("Malwina", "Wajdzik");
        warehouseHandlingSystem.createNewClient("Krysia", "Tomczak");
        warehouseHandlingSystem.addMetalIngot("KT1", GOLD, 300);
        warehouseHandlingSystem.addMetalIngot("KT1", GOLD, 700);
        warehouseHandlingSystem.addMetalIngot("MW0", GOLD, 300);
        warehouseHandlingSystem.addMetalIngot("MW0", COPPER, 2000);
    }
    @Test
    @DisplayName("Check if new client has been created")
    void checkIfNewClientHasBeenCreated() {
        // given - map with two clients, they have some metals in magazine
        warehouseHandlingSystem.createNewClient("Aleksandra", "Wiktoria");
        // when
        int numberOfClientsInMap = warehouseHandlingSystem.getNumberOfClients();
        // then
        Assertions.assertEquals(3, numberOfClientsInMap);
    }
    @Test
    @DisplayName("Check if client received the clientId")
    void checkIfNewClientReceivedCorrectClientId() {
        // given - map with two clients, they have some metals in magazine
        // when
        String newClientId = warehouseHandlingSystem.createNewClient("Aleksandra", "Wiktoria");
        // then
        Assertions.assertEquals("AW2", newClientId);
    }
    @Test
    @DisplayName("Check possibility of activating a premium account")
    void checkIfPossibleToActivatePremiumAccount() {
        // given - map with two clients, they have some metals in magazine
        // when
        warehouseHandlingSystem.activatePremiumAccount("MW0");
        Boolean isClientPremium = warehouseHandlingSystem.isPremiumClient("MW0");
        // then
        Assertions.assertEquals(true, isClientPremium);
    }
    @Test
    @DisplayName("Check if correct user received premium account")
    void checkToWhomYouActivatedPremiumAccount() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientWhomActivatedPremiumAccount = warehouseHandlingSystem.activatePremiumAccount("MW0");
        // then
        Assertions.assertEquals("MW0", clientWhomActivatedPremiumAccount);
    }
    @Test
    @DisplayName("Check system reaction on unknown user for 'activatePremiumAccount' calling")
    void  checkSystemReactionOnUnknownUserForActivatePremiumAccount() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientWhomActivatedPremiumAccount = warehouseHandlingSystem.activatePremiumAccount("AA8");
        // then
        Assertions.assertEquals("Client not found!" , clientWhomActivatedPremiumAccount);
    }
    @Test
    @DisplayName("Check what is clients' full name")
    void checkClientFullName() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientName = warehouseHandlingSystem.getClientFullName("MW0");
        // then
        Assertions.assertEquals("Malwina Wajdzik", clientName);
    }
    @Test
    @DisplayName("Check system reaction on unknown user for 'getClientFullName' calling")
    void  checkSystemReactionOnUnknownUserForClientFullName() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientName = warehouseHandlingSystem.getClientFullName("AA8");
        // then
        Assertions.assertEquals("Client not found!" , clientName);
    }
    @Test
    @DisplayName("Check what is the creation date of new client")
    void  checkClientCreationDate() {
        // given - map with two clients, they have some metals in magazine
        // when
        LocalDate newClientCreationDate = warehouseHandlingSystem.getClientCreationDate("MW0");
        LocalDate expectedCreationDate = LocalDate.now();
        // then
        Assertions.assertEquals(expectedCreationDate , newClientCreationDate);
    }
    @Test
    @DisplayName("Check system reaction on unknown user for 'getClientCreationDate' calling")
    void  checkSystemReactionOnUnknownUserForClientCreationDate() {
        // given - map with two clients, they have some metals in magazine
        // when
        LocalDate newClientCreationDate = warehouseHandlingSystem.getClientCreationDate("AA8");
        LocalDate expectedCreationDate = LocalDate.MIN;
        // then
        Assertions.assertEquals(expectedCreationDate , newClientCreationDate);
    }
    @Test
    @DisplayName("Check if client does not have premium account")
    void checkIfClientDoesNotHavePremiumAccount() {
        // given - map with two clients, they have some metals in magazine
        warehouseHandlingSystem.activatePremiumAccount("MW0");
        // when
        Boolean isClientPremiumKT1 = warehouseHandlingSystem.isPremiumClient("KT1");
        // then
        Assertions.assertEquals(false, isClientPremiumKT1);
    }
    @Test
    @DisplayName("Check if client has premium account")
    void checkIfClientHasPremiumAccount() {
        // given - map with two clients, they have some metals in magazine
        warehouseHandlingSystem.activatePremiumAccount("MW0");
        // when
        Boolean isClientPremiumMW0 = warehouseHandlingSystem.isPremiumClient("MW0");
        // then
        Assertions.assertEquals(true, isClientPremiumMW0);
    }
    @Test
    @DisplayName("Check system reaction on unknown user for 'IsPremium' calling")
    void checkSystemReactionOnUnknownUserForIsPremium() {
        // given - map with two clients, they have some metals in magazine
        // when
        Boolean isClientPremiumAA8 = warehouseHandlingSystem.isPremiumClient("AA8");
        // then
        Assertions.assertEquals(false, isClientPremiumAA8);
    }
    @Test
    @DisplayName("Check if number of clients in map is correct for two users")
    void checkNumberOfClientsInMap(){
        // given - map with two clients, they have some metals in magazine
        // when
        int finalNumberOfClientsMap = warehouseHandlingSystem.getNumberOfClients();
        // then
        Assertions.assertEquals(2, finalNumberOfClientsMap);
    }
    @Test
    @DisplayName("Check number of premium accounts")
    void checkNumberOfPremiumClients() {
        // given - map with two clients, they have some metals in magazine
        warehouseHandlingSystem.activatePremiumAccount("MW0");
        warehouseHandlingSystem.createNewClient("Aleksandra", "Wiktoria");
        warehouseHandlingSystem.activatePremiumAccount("AW2");
        // when
       int numberOfPremiumClients = warehouseHandlingSystem.getNumberOfPremiumClients();
        // then
        Assertions.assertEquals(2, numberOfPremiumClients);
    }
    @Test
    @DisplayName("Check if GOLD metal was added to clients account")
    void checkMetalIngotWhenThereWasOneMetal() {
        // given - map with two clients, they have some metals in magazine
        List<SupportedMetalType> storedMetalTypeKT1 = warehouseHandlingSystem.getStoredMetalTypesByClient("KT1");
        // when
        List<SupportedMetalType> expectedMetalTypes = new ArrayList<>(Collections.singleton(GOLD));
        // then
        Assertions.assertEquals( expectedMetalTypes, storedMetalTypeKT1);
    }
    @Test
    @DisplayName("Check if COPPER and GOLD metal was added to clients account")
    void checkMetalIngotWhenThereWereTwoMetals() {
        // given - map with two clients, they have some metals in magazine
        List<SupportedMetalType> storedMetalTypeMW0 = warehouseHandlingSystem.getStoredMetalTypesByClient("MW0");
        // when
        List<SupportedMetalType> expectedMetalTypes = new ArrayList<>(Set.of(GOLD, COPPER));
        // then
        assertTrue(expectedMetalTypes.containsAll(storedMetalTypeMW0));
    }
    @Test
    @DisplayName("Check system reaction on unknown user for 'addMetalIngot' calling")
    void checkSystemReactionOnUnknownUserForAddMetalIngot() {
        // given - map with two clients, they have some metals in magazine
        List<SupportedMetalType> storedMetalTypeAA8 = warehouseHandlingSystem.getStoredMetalTypesByClient("AA8");
        // when
        List<SupportedMetalType> expectedMetalTypes = new ArrayList<>();
        // then
        Assertions.assertEquals(expectedMetalTypes, storedMetalTypeAA8);
    }
    @Test
    @DisplayName("Check what is stored in magazine for client with one metal  ")
    void checkMetalTypesToMassStoredByClientWitOneMetalInMagazine() {
        // given - map with two clients, they have some metals in magazine
        Map<SupportedMetalType, Double> storedMetalTypesToMassKT1 = warehouseHandlingSystem.getMetalTypesToMassStoredByClient("KT1");
        // when
        Map<SupportedMetalType, Double> expectedMetalTypesToMass = new HashMap<>();
        expectedMetalTypesToMass.put(GOLD, 1000.0);
        // then
        Assertions.assertEquals(expectedMetalTypesToMass, storedMetalTypesToMassKT1);
    }
    @Test
    @DisplayName("Check system reaction on unknown user for 'addMetalIngot' calling")
    void checkSystemReactionOnUnknownUserForGetMetalTypesToMassStoredByClientWitOneMetalInMagazine() {
        // given - map with two clients, they have some metals in magazine
        // when
        Map<SupportedMetalType, Double> storedMetalTypesToMassAA8 = warehouseHandlingSystem.getMetalTypesToMassStoredByClient("AA8");
        // then
        Assertions.assertNull(storedMetalTypesToMassAA8);
    }
    @Test
    @DisplayName("Check what is stored in magazine for client with two metals")
    void checkMetalTypesToMassStoredByClientWitTwoMetalsInMagazine() {
        // given - map with two clients, they have some metals in magazine
        Map<SupportedMetalType, Double> storedMetalTypesToMassMW0 = warehouseHandlingSystem.getMetalTypesToMassStoredByClient("MW0");
        // when
        Map<SupportedMetalType, Double> expectedMetalTypesToMass = new HashMap<>();
        expectedMetalTypesToMass.put(GOLD, 300.0);
        expectedMetalTypesToMass.put(COPPER, 2000.0);
        // then
        Assertions.assertEquals(expectedMetalTypesToMass, storedMetalTypesToMassMW0);
    }
    @Test
    @DisplayName("Check what is the total volume occupied by client")
    void checkWhatIsTheTotalVolumeOccupiedByClient() {
        // given - map with two clients
        // when
        double totalVolumeOccupiedByClient = warehouseHandlingSystem.getTotalVolumeOccupiedByClient("KT1");
        // then
        Assertions.assertEquals(0.05181347150259067, totalVolumeOccupiedByClient);

    }
    @Test
    @DisplayName("Check system reaction on unknown user for 'getTotalVolumeOccupiedByClient' calling")
    void checkSystemReactionOnUnknownUserForGetTotalVolumeOccupiedByClient() {
        // given - map with two clients, they have some metals in magazine
        // when

        // then

    }
    @Test
    @DisplayName("Check what metal types are stored by client")
    void checkWhatMetalTypesAreStoredByClient() {
        // given - map with two clients
        warehouseHandlingSystem.createNewClient("Aleksandra", "Wiktoria");
        warehouseHandlingSystem.addMetalIngot("AW2", IRON, 500);
        warehouseHandlingSystem.addMetalIngot("AW2", SILVER, 1000);
        List<SupportedMetalType> storedMetalTypeAW2 = warehouseHandlingSystem.getStoredMetalTypesByClient("AW2");
        // when
        List<SupportedMetalType> expectedMetalTypes = new ArrayList<>(Set.of(IRON, SILVER));
        // then
        assertTrue(expectedMetalTypes.containsAll(storedMetalTypeAW2));
    }
    @Test
    @DisplayName("Check system reaction on unknown user for 'getStoredMetalTypesByClient' calling")
    void checkSystemReactionOnUnknownUserForGetStoredMetalTypesByClient() {
        // given - map with two clients, they have some metals in magazine
        // when

        // then

    }
}