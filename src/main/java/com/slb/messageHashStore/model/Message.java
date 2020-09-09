package com.slb.messageHashStore.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @NonNull
    private String message;
}