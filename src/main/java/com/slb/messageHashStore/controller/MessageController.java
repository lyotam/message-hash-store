package com.slb.messageHashStore.controller;

import com.slb.messageHashStore.model.Message;
import com.slb.messageHashStore.model.MessageDigest;
import com.slb.messageHashStore.service.MessageHashService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageController {

    @NonNull
    private final MessageHashService messageHashService;

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    public MessageDigest generateHash(@RequestBody Message message) {
        return messageHashService.generateMessageHash(message.getMessage());
    }

    @GetMapping("/messages/{hash}")
    @ResponseStatus(HttpStatus.OK)
    public Message getMessageByHash(@PathVariable String hash) {
        String message = messageHashService.retrieveMessage(hash);
        return Message.builder().message(message).build();
    }
}