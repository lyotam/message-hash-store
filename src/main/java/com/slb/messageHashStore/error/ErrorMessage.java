package com.slb.messageHashStore.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ErrorMessage {

    @NonNull
    @JsonProperty("err_msg")
    private final String message;
}
