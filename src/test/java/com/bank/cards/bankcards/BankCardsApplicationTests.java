package com.bank.cards.bankcards;

import com.bank.cards.bankcards.register.controller.RegisterNumberRequest;
import com.bank.cards.bankcards.register.controller.RegisterNumberResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.bank.cards.bankcards.ObjectMother.createValidRegisterNumberRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BankCardsApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void register_shouldReturnGivenNumberWithCreatedStatus() {
        RegisterNumberRequest registerNumberRequest = createValidRegisterNumberRequest();
        ResponseEntity<RegisterNumberResponse> response = restTemplate
                .postForEntity("/registers",
                        registerNumberRequest, RegisterNumberResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(registerNumberRequest.getNumber(), response.getBody().getNumber());
    }
}
