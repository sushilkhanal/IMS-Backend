package com.example.ims.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.ims.Model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();

   
}
