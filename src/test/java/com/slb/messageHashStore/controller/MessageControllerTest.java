package com.slb.messageHashStore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slb.messageHashStore.error.MessageNotFoundException;
import com.slb.messageHashStore.model.Message;
import com.slb.messageHashStore.model.MessageDigest;
import com.slb.messageHashStore.service.MessageHashService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageHashService messageHashService;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void resetMock() {
        reset(messageHashService);
    }

    @Nested
    class TestGenerateHash {

        @Test
        @DisplayName("Given valid request body, a MessageDigest is returned")
        void test_generate_ok() throws Exception {
            String expected = "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae";
            when(messageHashService.generateMessageHash(anyString())).thenReturn(expected);

            String res = executePostRequest(mockMvc, "/messages", "{\"message\": \"foo\"}",
                    MockMvcResultMatchers.status().isOk());
            assertNotNull(res);
            assertEquals(MessageDigest.builder().digest(expected).build(),
                    objectMapper.readValue(res, MessageDigest.class));
        }

        @Test
        @DisplayName("Given invalid request body, a BadRequest response is returned")
        void test_generate_bad_request() throws Exception {
            executePostRequest(mockMvc, "/messages", "{\"message\": null}",
                    MockMvcResultMatchers.status().isBadRequest());
        }
    }

    @Nested
    class TestGetMessageByHash {

        @Test
        @DisplayName("Given existing hash, a Message is returned")
        void test_message_by_hash_ok() throws Exception {
            String expected = "foo";
            when(messageHashService.retrieveMessage(anyString())).thenReturn(expected);

            String res = executeGetRequest(mockMvc,
                    "/messages/2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae",
                    MockMvcResultMatchers.status().isOk());
            assertNotNull(res);
            assertEquals(Message.builder().message(expected).build(),
                    objectMapper.readValue(res, Message.class));
        }

        @Test
        @DisplayName("Given non existing hash, a NotFound response is returned")
        void test_generate_bad_request() throws Exception {
            when(messageHashService.retrieveMessage(anyString())).thenThrow(new MessageNotFoundException("Message not found"));
            executeGetRequest(mockMvc, "/messages/aaaa", MockMvcResultMatchers.status().isNotFound());
        }
    }

    private String executeGetRequest(MockMvc mockMvc, String resource, ResultMatcher responseStatus) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.get(resource)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(responseStatus)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String executePostRequest(MockMvc mockMvc, String resource, String body, ResultMatcher responseStatus) throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.post(resource).content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(responseStatus)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}