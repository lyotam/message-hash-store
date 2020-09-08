package com.slb.messageHashStore.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "hash_messages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashMessage {

    @Id
    @NonNull
    private String digest;

    @Column(nullable = false)
    @NonNull
    private String message;
}