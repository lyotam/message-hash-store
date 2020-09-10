package com.slb.messageHashStore.repository;


import com.slb.messageHashStore.model.HashMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageHashRepository extends MongoRepository<HashMessage, String> {}