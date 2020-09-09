package com.slb.messageHashStore.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDigest {

    @NonNull
    private String digest;
}