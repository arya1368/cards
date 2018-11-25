package com.bank.cards.bankcards.register.controller;

import javax.validation.constraints.Size;

public class RegisterNumberRequest {

    @Size(min = 11)
    private String number;

    public RegisterNumberRequest() {

    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
