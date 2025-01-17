package com.hepsiemlak.todo.service;

import com.hepsiemlak.todo.exception.BusinessException;
import com.hepsiemlak.todo.exception.NotFoundException;
import com.hepsiemlak.todo.model.User;
import com.hepsiemlak.todo.model.dto.UserRequest;
import com.hepsiemlak.todo.model.dto.UserResponse;
import com.hepsiemlak.todo.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByToken(String token){
        return userRepository.findByToken(token);
    }

    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email,password);
        if(userOptional.isPresent()){
            String token = UUID.randomUUID().toString();
            User user = userOptional.get();
            user.setToken(token);
            userRepository.save(user);
            return token;
        }
        return StringUtils.EMPTY;
    }

    public UserResponse register(UserRequest userRequest){
        if(userRepository.existsByEmail(userRequest.getEmail())){
            throw new BusinessException("Email already exists!");
        }
        User user = userRequest.toUser();
        user.setCreateDate(Instant.now());
        userRepository.save(user);

        UserResponse userResponse = UserResponse.builder()
                .Id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate())
                .build();

        return userResponse;
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername())
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }
}
