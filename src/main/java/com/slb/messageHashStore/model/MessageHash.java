package com.slb.messageHashStore.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MessageHash {

    @NonNull
    private final String digest;
}