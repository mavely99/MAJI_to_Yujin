package com.example.maji.service;

import com.example.maji.model.UserEntity;
import com.example.maji.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity saveName(String name) {
        UserEntity entity = new UserEntity();
        entity.setUserName(name);
        return userRepository.save(entity);
    }

}
