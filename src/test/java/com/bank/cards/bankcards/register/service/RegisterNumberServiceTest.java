package com.bank.cards.bankcards.register.service;

import com.bank.cards.bankcards.ObjectMother;
import com.bank.cards.bankcards.notifier.service.NotifierService;
import com.bank.cards.bankcards.register.controller.RegisterNumberRequest;
import com.bank.cards.bankcards.register.controller.RegisterNumberResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterNumberServiceTest {

    private static final String CODE = "code";

    @InjectMocks
    private RegisterNumberService service;

    @Mock
    private CodeGenerator codeGenerator;

    @Mock
    private NotifierService notifierService;

    private RegisterNumberRequest registerNumber;

    @BeforeEach
    void setUp() {
        registerNumber = ObjectMother.createValidRegisterNumberRequest();
    }

    @Test
    void register_shouldPutGivenNumberInRegisteredNumbers() {
        service.register(registerNumber);
        assertTrue(service.registeredNumbers.containsKey(registerNumber.getNumber()));
    }

    @Test
    void register_shouldReturnRegisterNumberResponseThatContainsGivenNumber() {
        RegisterNumberResponse actual = service.register(registerNumber);
        assertEquals(registerNumber.getNumber(), actual.getNumber());
    }

    @Test
    void register_shouldGenerateCodeForGivenNumber() {
        when(codeGenerator.generate()).thenReturn(CODE);
        service.register(registerNumber);
        String actualCode = service.registeredNumbers.get(registerNumber.getNumber());
        assertEquals(CODE, actualCode);
    }

    @Test
    void register_whenANumberRegister2Times_registeredNumbersShouldHaveSecondGeneratedCodeForNumber() {
        String secondCode = "code2";
        when(codeGenerator.generate()).thenReturn(CODE, secondCode);
        service.register(registerNumber);
        service.register(registerNumber);
        String actualCode = service.registeredNumbers.get(registerNumber.getNumber());
        assertEquals(secondCode, actualCode);
    }

    @Test
    void register_shouldCallNotifierSendMethod() {
        when(codeGenerator.generate()).thenReturn(CODE);
        service.register(registerNumber);
        verify(notifierService).send(CODE);
    }
}