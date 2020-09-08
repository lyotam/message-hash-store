package com.slb.messageHashStore.repository;


import com.slb.messageHashStore.model.HashMessage;
import org.springframework.data.repository.CrudRepository;

public interface MessageHashRepository extends CrudRepository<HashMessage, String> {}