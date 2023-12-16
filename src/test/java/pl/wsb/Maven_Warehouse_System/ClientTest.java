package pl.wsb.Maven_Warehouse_System;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class ClientTest {
    Client clientTest = new Client();
    private final WarehouseHandlingSystem warehouseHandlingSystem = new WarehouseHandlingSystem();
    private final DataContainer dataContainer = new DataContainer();
    @BeforeEach
    void createClient() {
        LocalDate todayDate = LocalDate.now();
        Client client = new Client("First Name", "Last Name", "QA1", todayDate);
        dataContainer.clients.put("QA1",client);
    }
    @Test
    @DisplayName("Check if new client does not have premium account")
    void checkIfClientDoesNotHavePremium() {
        // given - new client created, available in map
        // when
        Boolean isPremium = dataContainer.clients.get("QA1").getIsPremium();
        // then
        Assertions.assertEquals(false, isPremium);
    }
    @Test
    @DisplayName("Check if new client has premium account")
    void checkIfClientHasPremium() {
        // given - new client created, available in map
        dataContainer.clients.get("QA1").setIsPremium( true );
        // when
        Boolean isPremium = dataContainer.clients.get("QA1").getIsPremium();
        // then
        Assertions.assertEquals(true, isPremium);
    }
    @Test
    @DisplayName("Check if new client has correct Id")
    void checkIfClientHasCorrectId() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientId = dataContainer.clients.get("QA1").getClientId();
        // then
        Assertions.assertEquals("QA1", clientId);
    }
    @Test
    @DisplayName("Check if new client's name'")
    void getFirstName() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientFirstName = dataContainer.clients.get("QA1").getFirstName();
        // then
        Assertions.assertEquals("First Name", clientFirstName);
    }
    @Test
    @DisplayName("Check if new client's name'")
    void getLastName() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientLastName = dataContainer.clients.get("QA1").getLastName();
        // then
        Assertions.assertEquals("Last Name", clientLastName);
    }
    @Test
    void getCreationDate() {
        // given - map with two clients, they have some metals in magazine
        // when
        LocalDate creationDate = dataContainer.clients.get("QA1").getCreationDate();
        LocalDate expectedCreationDate = LocalDate.now();
        // then
        Assertions.assertEquals(expectedCreationDate, creationDate);
    }
    @Test
    @DisplayName("Check if app can set premium account for new client")
    void checkIfPremiumCanBeSet() {
        // given - new client created, available in map
        LocalDate todayDate = LocalDate.now();
        Client client2 = new Client("First Name2", "Last Name2", "QA2", todayDate);
        dataContainer.clients.put("QA2",client2);
        // when
        dataContainer.clients.get("QA2").setIsPremium( true );
        Boolean isPremium = dataContainer.clients.get("QA2").getIsPremium();
        // then
        Assertions.assertEquals(true, isPremium);
    }
}