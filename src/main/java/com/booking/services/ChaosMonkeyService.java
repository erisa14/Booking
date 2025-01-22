package com.booking.services;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ChaosMonkeyService {
    private final Random random = new Random();

    public void simulateFailure() {
        if (random.nextBoolean()) {
            throw new RuntimeException("Simulated database failure!");
        }
    }
}
