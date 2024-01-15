package com.nashtechglobal.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MongoDBRepository<E, K> extends MongoRepository<E, K> {

}
