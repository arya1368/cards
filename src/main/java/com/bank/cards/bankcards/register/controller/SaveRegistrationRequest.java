package com.bank.cards.bankcards.register.controller;

import java.util.Objects;

public class SaveRegistrationRequest {
    private String registerCode;

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaveRegistrationRequest)) return false;
        SaveRegistrationRequest that = (SaveRegistrationRequest) o;
        return Objects.equals(registerCode, that.registerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registerCode);
    }
}
