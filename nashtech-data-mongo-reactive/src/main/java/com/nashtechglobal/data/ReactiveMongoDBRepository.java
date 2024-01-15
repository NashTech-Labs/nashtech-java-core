package com.nashtechglobal.data;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReactiveMongoDBRepository<E, K>
        extends ReactiveMongoRepository<E, K> {
}
