package com.sb.messageHashStore.controller;

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

    @GetMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    public String getBooks(@RequestBody String message) {
        return messageHashService.generateMessageHash(message);
    }
}
