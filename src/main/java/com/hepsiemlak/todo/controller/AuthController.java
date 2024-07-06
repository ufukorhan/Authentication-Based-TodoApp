package com.hepsiemlak.todo.controller;

import com.hepsiemlak.todo.exception.NotFoundException;
import com.hepsiemlak.todo.model.dto.UserRequest;
import com.hepsiemlak.todo.model.dto.UserResponse;
import com.hepsiemlak.todo.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.util.Map.entry;

@RestController
@RequestMapping("/auth")
public class AuthController {
    // TODO: Return ResponseEntity Object with related status code


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, String> auth(@RequestParam("email") final String email, @RequestParam("password") final String password) {
        String token = authService.login(email, password);
        if (StringUtils.isEmpty(token)) {
            throw new NotFoundException("Token not found!");
        }
        return Map.ofEntries(entry("token", token));
    }

    @PostMapping("/register")
    public UserResponse save(@Valid @RequestBody UserRequest userRequest) {
        return authService.register(userRequest);
    }
}
