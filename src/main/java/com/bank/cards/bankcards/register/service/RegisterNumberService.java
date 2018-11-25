package com.bank.cards.bankcards.register.service;

import com.bank.cards.bankcards.notifier.service.NotifierService;
import com.bank.cards.bankcards.register.controller.RegisterNumberRequest;
import com.bank.cards.bankcards.register.controller.RegisterNumberResponse;
import com.bank.cards.bankcards.register.controller.SaveRegistrationRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterNumberService {

    Map<String, String> registeredNumbers;
    private CodeGenerator codeGenerator;
    private NotifierService notifierService;

    public RegisterNumberService(CodeGenerator codeGenerator, NotifierService notifierService) {
        registeredNumbers = new HashMap<>();
        this.codeGenerator = codeGenerator;
        this.notifierService = notifierService;
    }

    public RegisterNumberResponse register(RegisterNumberRequest registerNumber) {
        String code = codeGenerator.generate();
        registeredNumbers.put(registerNumber.getNumber(), code);
        notifierService.send(code);
        return new RegisterNumberResponse(registerNumber.getNumber());
    }

    public RegisterNumberResponse save(String number, SaveRegistrationRequest request) {
        return null;
    }
}
