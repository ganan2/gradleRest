package com.product.rest.repos;

import com.product.rest.pojos.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User, Integer>{
    User findByName(String name);
}
