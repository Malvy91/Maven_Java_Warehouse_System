package pl.wsb.Maven_Warehouse_System;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.fail;

class WarehouseHandlingSystemTest {

    private final WarehouseHandlingSystem warehouseHandlingSystem = new WarehouseHandlingSystem();
    @BeforeEach
    void createClientsMap() {
        warehouseHandlingSystem.createNewClient("Malwina", "Wajdzik");
        warehouseHandlingSystem.createNewClient("Krysia", "Tomczak");
    }
    @Test
    @DisplayName("Check if new client has been created")
    void checkIfNewClientHasBeenCreated() {
        // given - map with two clients
        warehouseHandlingSystem.createNewClient("Aleksandra", "Wiktoria");
        // when
        int numberOfClientsInMap = warehouseHandlingSystem.getNumberOfClients();
        // then
        Assertions.assertEquals(3, numberOfClientsInMap);
    }
    @Test
    @DisplayName("Check if client received the clientId")
    void checkIfNewClientReceivedCorrectClientId() {
        // given - map with two clients
        // when
        String newClientId = warehouseHandlingSystem.createNewClient("Aleksandra", "Wiktoria");
        // then
        Assertions.assertEquals("AW2", newClientId);
    }
    @Test
    @DisplayName("Check possibility of activating a premium account")
    void checkIfPossibleToActivatePremiumAccount() {
        // given - map with two clients
        // when
        warehouseHandlingSystem.activatePremiumAccount("MW0");
        Boolean isClientPremium = warehouseHandlingSystem.isPremiumClient("MW0");
        // then
        Assertions.assertEquals(true, isClientPremium);
    }
    @Test
    @DisplayName("Check if correct user received premium account")
    void checkToWhomYouActivatedPremiumAccount() {
        // given - map with two clients
        // when
        String clientWhomActivatedPremiumAccount = warehouseHandlingSystem.activatePremiumAccount("MW0");
        // then
        Assertions.assertEquals("MW0", clientWhomActivatedPremiumAccount);
    }
    @Test
    @DisplayName("Check what is clients' full name")
    void checkClientFullName() {
        // given - map with two clients
        // when
        String clientName = warehouseHandlingSystem.getClientFullName("MW0");
        // then
        Assertions.assertEquals("Malwina Wajdzik", clientName);
    }
    @Test
    @DisplayName("Check what is the creation date of new client")
    void  checkClientCreationDate() {
        // given - map with two clients
        // when
        LocalDate newClientCreationDate = warehouseHandlingSystem.getClientCreationDate("MW0");
        LocalDate expectedCreationDate = LocalDate.now();
        // then
        Assertions.assertEquals(expectedCreationDate , newClientCreationDate);
    }
    @Test
    @DisplayName("Check if client does not have premium account")
    void checkIfClientDoesNotHavePremiumAccount() {
        // given - map with two clients
        warehouseHandlingSystem.activatePremiumAccount("MW0");
        // when
        Boolean isClientPremiumKT1 = warehouseHandlingSystem.isPremiumClient("KT1");
        // then
        Assertions.assertEquals(false, isClientPremiumKT1);
    }
    @Test
    @DisplayName("Check if client has premium account")
    void checkIfClientHasPremiumAccount() {
        // given - map with two clients
        warehouseHandlingSystem.activatePremiumAccount("MW0");
        // when
        Boolean isClientPremiumMW0 = warehouseHandlingSystem.isPremiumClient("MW0");
        // then
        Assertions.assertEquals(true, isClientPremiumMW0);
    }
    @Test
    @DisplayName("Check if number of clients in map is correct for two users")
    void checkNumberOfClientsInMap(){
        // given - map with two clients
        // when
        int finalNumberOfClientsMap = warehouseHandlingSystem.getNumberOfClients();
        // then
        Assertions.assertEquals(2, finalNumberOfClientsMap);
    }
    @Test
    @DisplayName("Check number of premium accounts")
    void checkNumberOfPremiumClients() {
        // given - map with two clients
        warehouseHandlingSystem.activatePremiumAccount("MW0");
        warehouseHandlingSystem.createNewClient("Aleksandra", "Wiktoria");
        warehouseHandlingSystem.activatePremiumAccount("AW2");
        // when
       int numberOfPremiumClients = warehouseHandlingSystem.getNumberOfPremiumClients();
        // then
        Assertions.assertEquals(2, numberOfPremiumClients);
    }
    @org.junit.jupiter.api.Test
    void addMetalIngot() {
        // given - map with two clients

        // when

        // then

    }
    @org.junit.jupiter.api.Test
    void getMetalTypesToMassStoredByClient() {
        // given - map with two clients

        // when

        // then

    }
    @org.junit.jupiter.api.Test
    void getTotalVolumeOccupiedByClient() {
        // given - map with two clients

        // when

        // then

    }
    @org.junit.jupiter.api.Test
    void getStoredMetalTypesByClient() {
        // given - map with two clients

        // when

        // then

    }
}