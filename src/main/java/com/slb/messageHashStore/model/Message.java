package com.slb.messageHashStore.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Message {

    @NonNull
    private final String message;
}
