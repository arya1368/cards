package com.bank.cards.bankcards.register.controller;

import com.bank.cards.bankcards.register.service.RegisterNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegisterNumberController {

    private RegisterNumberService registerNumberService;

    public RegisterNumberController(RegisterNumberService registerNumberService) {
        this.registerNumberService = registerNumberService;
    }

    @PostMapping("/registers")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterNumberResponse registerNumber(@Valid @RequestBody RegisterNumberRequest request) {
        return registerNumberService.register(request);
    }

    @PostMapping("/registers/{number}")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterNumberResponse saveRegistration(@PathVariable String number, @RequestBody SaveRegistrationRequest request) {
        return registerNumberService.save(number, request);
    }
}
