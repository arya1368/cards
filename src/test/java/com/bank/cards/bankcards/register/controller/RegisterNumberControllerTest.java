package com.bank.cards.bankcards.register.controller;

import com.bank.cards.bankcards.register.service.RegisterNumberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.bank.cards.bankcards.ObjectMother.*;
import static com.bank.cards.bankcards.TestUtils.convertToJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RegisterNumberController.class)
class RegisterNumberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterNumberService service;

    private static final String NUMBER = "09123";

    @Test
    void registerNumber_whenGivenValidNumber_shouldReturnGivenNumberWithCreatedStatus() throws Exception {
        RegisterNumberRequest request = createValidRegisterNumberRequest();
        when(service.register(any()))
                .thenReturn(new RegisterNumberResponse(request.getNumber()));

        mockMvc.perform(registerPostReq(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("number").value(request.getNumber()));
    }

    @Test
    void registerNumber_whenGivenInvalidNumber_shouldReturnWithBadRequestStatus() throws Exception {
        RegisterNumberRequest request = createInvalidRegisterNumberRequest();

        mockMvc.perform(registerPostReq(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    void saveRegistration_whenGivenValidCodeForNumber_shouldReturnNumberWithCreatedStatus() throws Exception {
        SaveRegistrationRequest registrationRequest = createSaveRegistrationRequest();
        when(service.save(NUMBER, registrationRequest)).thenReturn(new RegisterNumberResponse(NUMBER));

        mockMvc.perform(post("/registers/" + NUMBER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(registrationRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("number").value(NUMBER));
    }

    @Test
    void saveRegistration_whenNumberIsNotInRegisteredNumbers_shouldReturnWithNotFoundStatus() {

    }

    private MockHttpServletRequestBuilder registerPostReq(RegisterNumberRequest requestBody) throws Exception {
        String requestAsJson = convertToJson(requestBody);
        return post("/registers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestAsJson);
    }
}
