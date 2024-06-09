package com.uninorte.Ucommerce.repository;

import com.uninorte.Ucommerce.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
