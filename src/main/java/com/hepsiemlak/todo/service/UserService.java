package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.exception.BusinessException;
import com.hepsiemlak.todo.model.User;
import com.hepsiemlak.todo.model.dto.UserRequest;
import com.hepsiemlak.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    public User getUser(){
        return authService.getAuthenticatedUser();
    }

    public void deleteUser(){
        // TODO : it should be soft delete

        User user = authService.getAuthenticatedUser();
        userRepository.delete(user);
    }

    public User updateUser(UserRequest userRequest){
        User existUser = authService.getAuthenticatedUser();
        System.out.println(existUser);
        if(!existUser.getEmail().equals(userRequest.getEmail()) && userRepository.existsByEmail(userRequest.getEmail())) {
            throw new BusinessException("Email already exists!");
        }
        User user = userRequest.toUser();
        user.setId(existUser.getId());
        user.setToken(existUser.getToken());
        user.setCreateDate(existUser.getCreateDate());
        user.setUpdateDate(Instant.now());
        return userRepository.save(user);
    }
}
