package pl.wsb.Maven_Warehouse_System;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientIdGeneratorTest {
    private final ClientIdGenerator clientIdGenerator = new ClientIdGenerator();
    @Test
    @DisplayName("Check if clientId can be generated for 10")
    void checkIfClientIdCanBeGeneratedFor10() {
        // given
        // when
        String clientId = clientIdGenerator.generateClientId("Aleksandra", "Aleksandrowicz", 10);
        // then
        Assertions.assertEquals("AAa", clientId);

    }
    @Test
    @DisplayName("Check if clientId can be generated for 500")
    void checkIfClientIdCanBeGeneratedFor500() {
        // given
        // when
        String clientId = clientIdGenerator.generateClientId("Michał", "Krakowski", 500);
        // then
        Assertions.assertEquals("MK1f4", clientId);

    }
}