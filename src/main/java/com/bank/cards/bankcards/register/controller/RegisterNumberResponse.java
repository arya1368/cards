package com.bank.cards.bankcards.register.controller;

public class RegisterNumberResponse {

    private String number;

    public RegisterNumberResponse() {
    }

    public RegisterNumberResponse(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
