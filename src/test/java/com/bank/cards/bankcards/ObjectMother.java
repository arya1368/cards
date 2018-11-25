package com.bank.cards.bankcards;

import com.bank.cards.bankcards.register.controller.RegisterNumberRequest;
import com.bank.cards.bankcards.register.controller.SaveRegistrationRequest;

public class ObjectMother {

    public static RegisterNumberRequest createValidRegisterNumberRequest() {
        RegisterNumberRequest request = new RegisterNumberRequest();
        request.setNumber("09121234567");
        return request;
    }

    public static RegisterNumberRequest createInvalidRegisterNumberRequest() {
        RegisterNumberRequest request = new RegisterNumberRequest();
        request.setNumber("Invalid");
        return request;
    }

    public static SaveRegistrationRequest createSaveRegistrationRequest() {
        SaveRegistrationRequest registrationRequest = new SaveRegistrationRequest();
        registrationRequest.setRegisterCode("code");
        return registrationRequest;
    }
}
