package com.sb.messageHashStore.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MessageHashRequest {

    @NonNull
    private final String message;
}
