package com.bank.cards.bankcards.notifier.service;

import org.springframework.stereotype.Service;

@Service
public class ConsoleNotifierService implements NotifierService {

    @Override
    public void send(String code) {
        System.out.println("Register code: " + code);
    }
}
