package pl.wsb.Maven_Warehouse_System;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static pl.wsb.Maven_Warehouse_System.SupportedMetalType.*;

class VerifyCorrectnessTest {
    private final VerifyCorrectness verifyCorrectness = new VerifyCorrectness();
    @Test
    @DisplayName("Check total volume occupied by client")
    void checkTotalVolumeOccupiedByOneClients() {
        // given
        Map<SupportedMetalType, Double> availableMetalsAndItsMasses = new HashMap<>();
        availableMetalsAndItsMasses.put(GOLD, 300.0);
        availableMetalsAndItsMasses.put(COPPER, 2000.0);
        verifyCorrectness.clientsMap.put("MW0", availableMetalsAndItsMasses);
        // when
        double totalVolume = verifyCorrectness.getTotalVolumeOccupiedByAllClients();
        // then
        Assertions.assertEquals(2300.0, totalVolume);
    }
    @Test
    @DisplayName("Check total volume occupied by two clients")
    void checkTotalVolumeOccupiedByAllClients() {
        // given
        Map<SupportedMetalType, Double> availableMetalsAndItsMasses = new HashMap<>();
        availableMetalsAndItsMasses.put(GOLD, 300.0);
        availableMetalsAndItsMasses.put(COPPER, 2000.0);
        verifyCorrectness.clientsMap.put("MW0", availableMetalsAndItsMasses);
        // when
        Map<SupportedMetalType, Double> availableMetalsAndItsMasses2 = new HashMap<>();
        availableMetalsAndItsMasses.put(PLATINUM, 1000.0);
        availableMetalsAndItsMasses.put(TUNGSTEN, 4000.0);
        verifyCorrectness.clientsMap.put("KT1", availableMetalsAndItsMasses2);
        double totalVolume = verifyCorrectness.getTotalVolumeOccupiedByAllClients();
        // then
        Assertions.assertEquals(7300.0, totalVolume);
    }
    @Test
    @DisplayName("Check application response then there is no client in map")
    void checkApplicationResponseForUnknownUser() {
        // given - empty map
        // when
        double totalVolume = verifyCorrectness.getTotalVolumeOccupiedByAllClients();
        // then
        Assertions.assertEquals(0.0, totalVolume);
    }
}