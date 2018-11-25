package com.bank.cards.bankcards.register.service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomCodeGenerator implements CodeGenerator {

    @Override
    public String generate() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
}
