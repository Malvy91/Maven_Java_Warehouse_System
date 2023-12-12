package pl.wsb.Maven_Warehouse_System;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static pl.wsb.Maven_Warehouse_System.SupportedMetalType.*;

class CorrectnessVerificationTest {
    private final CorrectnessVerification correctnessVerification = new CorrectnessVerification();
    private final DataContainer dataContainer = new DataContainer();
    @Test
    @DisplayName("Check total volume occupied by client")
    void checkTotalVolumeOccupiedByOneClients() {
        // given
        Map<String, Map<SupportedMetalType, Double>> clientsMap = new HashMap<>();
        Map<SupportedMetalType, Double> availableMetalsAndItsMasses = new HashMap<>();
        availableMetalsAndItsMasses.put(GOLD, 300.0);
        availableMetalsAndItsMasses.put(COPPER, 2000.0);
        clientsMap.put("MW0", availableMetalsAndItsMasses);
        // when
        double totalVolume = correctnessVerification.getTotalVolumeOccupiedByAllClients(clientsMap);
        // then
        Assertions.assertEquals(2300.0, totalVolume);
    }
    @Test
    @DisplayName("Check total volume occupied by two clients")
    void checkTotalVolumeOccupiedByAllClients() {
        // given
        Map<String, Map<SupportedMetalType, Double>> clientsMap = new HashMap<>();
        Map<SupportedMetalType, Double> availableMetalsAndItsMasses = new HashMap<>();
        availableMetalsAndItsMasses.put(GOLD, 300.0);
        availableMetalsAndItsMasses.put(COPPER, 2000.0);
        clientsMap.put("MW0", availableMetalsAndItsMasses);
        // when
        Map<SupportedMetalType, Double> availableMetalsAndItsMasses2 = new HashMap<>();
        availableMetalsAndItsMasses.put(PLATINUM, 1000.0);
        availableMetalsAndItsMasses.put(TUNGSTEN, 4000.0);
        clientsMap.put("KT1", availableMetalsAndItsMasses2);
        double totalVolume = correctnessVerification.getTotalVolumeOccupiedByAllClients(clientsMap);
        // then
        Assertions.assertEquals(7300.0, totalVolume);
    }
    @Test
    @DisplayName("Check application response then there is no client in map")
    void checkApplicationResponseForUnknownUser() {
        // given - empty map
        // when
        double totalVolume = correctnessVerification.getTotalVolumeOccupiedByAllClients(dataContainer.clientsMap);
        // then
        Assertions.assertEquals(0.0, totalVolume);
    }
}