package com.slb.messageHashStore.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashMessage {

    @Id
    @NonNull
    private String digest;

    @NonNull
    private String message;
}