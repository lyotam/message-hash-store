package com.slb.messageHashStore.controller;

import com.slb.messageHashStore.model.Message;
import com.slb.messageHashStore.model.MessageHash;
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
    public MessageHash generateHash(@RequestBody String message) {
        return messageHashService.generateMessageHash(message);
    }

    @GetMapping("/messages/{hash}")
    @ResponseStatus(HttpStatus.OK)
    public Message getHash(@PathVariable String hash) {
        return messageHashService.retreiveMessageHash(hash);
    }
}
