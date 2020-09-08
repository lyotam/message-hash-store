package com.slb.messageHashStore.service;

import com.slb.messageHashStore.error.MessageNotFoundException;
import com.slb.messageHashStore.model.HashMessage;
import com.slb.messageHashStore.repository.MessageHashRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageHashService {

    @NonNull
    private final MessageHashRepository messageHashRepository;

    public String generateMessageHash(String message) {
        String digest = DigestUtils.sha256Hex(message);
        if (!messageHashRepository.existsById(digest)) {
            messageHashRepository.save(HashMessage.builder().digest(digest).message(message).build());
        }
        return digest;
    }

    public String retrieveMessage(String hash) {
        return messageHashRepository
                .findById(hash)
                .orElseThrow(() -> new MessageNotFoundException("Message not found"))
                .getMessage();
    }
}