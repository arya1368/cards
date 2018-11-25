package com.bank.cards.bankcards.register.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomCodeGeneratorTest {

    @Test
    void generate_shouldGenerate4CharsCode() {
        RandomCodeGenerator generator = new RandomCodeGenerator();
        String actualCode = generator.generate();
        assertEquals(4, actualCode.length());
    }
}