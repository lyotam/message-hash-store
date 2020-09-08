package com.slb.messageHashStore.service;

import com.slb.messageHashStore.error.MessageNotFoundException;
import com.slb.messageHashStore.model.HashMessage;
import com.slb.messageHashStore.repository.MessageHashRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageHashServiceTest {

    @Mock
    private MessageHashRepository messageHashRepository;

    @InjectMocks
    private MessageHashService messageHashService;

    @AfterEach
    void resetMock() {
        reset(messageHashRepository);
    }

    @Nested
    class TestGenerateMessageHash {

        @Test
        @DisplayName("Given a string, a hash digest is returned")
        void test_generate_ok() {
            String expected = "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae";
            when(messageHashRepository.existsById(anyString())).thenReturn(true);

            String res = messageHashService.generateMessageHash("foo");
            assertNotNull(res);
            assertEquals(expected, res);
        }
    }

    @Nested
    class TestRetrieveMessage {

        @Test
        @DisplayName("Given an existing hash, a message string is returned")
        void test_retrieve_ok() {
            String hash = "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae";
            String expected = "foo";
            when(messageHashRepository.findById(anyString())).thenReturn(
                    Optional.of(HashMessage.builder()
                            .digest(expected)
                            .message("foo").build()));

            String res = messageHashService.retrieveMessage(hash);
            assertNotNull(res);
            assertEquals(expected, res);
        }

        @Test
        @DisplayName("Given non existing hash, a MessageNotFound exception is thrown")
        void test_generate_bad_request() {
            when(messageHashRepository.findById(anyString())).thenReturn(Optional.empty());

            assertThrows(MessageNotFoundException.class, () -> messageHashService.retrieveMessage("aaaa"));
        }
    }
}