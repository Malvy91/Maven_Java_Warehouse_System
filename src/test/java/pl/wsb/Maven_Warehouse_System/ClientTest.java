package pl.wsb.Maven_Warehouse_System;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class ClientTest {
    Client clientTest = new Client();
    @BeforeEach
    void createClient() {
        LocalDate todayDate = LocalDate.now();
        Client client = new Client("First Name", "Last Name", "QA1", todayDate);
        clientTest.clients.put("QA1",client);
    }
    @Test
    @DisplayName("Check if new client does not have premium account")
    void checkIfClientDoesNotHavePremium() {
        // given - new client created, available in map
        // when
        Boolean isPremium = clientTest.getPremium("QA1");
        // then
        Assertions.assertEquals(false, isPremium);
    }
    @Test
    @DisplayName("Check if new client has premium account")
    void checkIfClientHasPremium() {
        // given - new client created, available in map
        clientTest.setIsPremium("QA1", true);
        // when
        Boolean isPremium = clientTest.getPremium("QA1");
        // then
        Assertions.assertEquals(true, isPremium);
    }
    @Test
    @DisplayName("Check if new client has correct Id")
    void checkIfClientHasCorrectId() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientId = clientTest.getClientId("QA1");
        // then
        Assertions.assertEquals("QA1", clientId);
    }
    @Test
    @DisplayName("Check if new client's name'")
    void getFirstName() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientFirstName = clientTest.getFirstName("QA1");
        // then
        Assertions.assertEquals("First Name", clientFirstName);
    }
    @Test
    @DisplayName("Check if new client's name'")
    void getLastName() {
        // given - map with two clients, they have some metals in magazine
        // when
        String clientLastName = clientTest.getLastName("QA1");
        // then
        Assertions.assertEquals("Last Name", clientLastName);
    }
    @Test
    void getCreationDate() {
        // given - map with two clients, they have some metals in magazine
        // when
        LocalDate creationDate = clientTest.getCreationDate("QA1");
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
        clientTest.clients.put("QA2",client2);
        // when
        clientTest.setIsPremium("QA2", true);
        Boolean isPremium = clientTest.getPremium("QA2");
        // then
        Assertions.assertEquals(true, isPremium);
    }
    @Test
    void createClientObject() {
        // given - map with two clients, they have some metals in magazine
        int beforeAddingNewClient = clientTest.clients.size();
        // when
        LocalDate todayDate = LocalDate.now();
        Client client3 = clientTest.createClientObject("First Name3", "Last Name3", "QA3", todayDate);
        clientTest.clients.put("QA3",client3);
        int afterAddingNewClient = clientTest.clients.size();
        // then
        Assertions.assertEquals(beforeAddingNewClient+1, afterAddingNewClient);
    }
}