package com.hepsiemlak.todo.model.dto;


import com.hepsiemlak.todo.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class UserRequest {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;

    @NotEmpty
    @Size(min = 3, message = "user name should have at least 2 characters")
    private String name;

    @NotEmpty
    @Size(min = 2, message = "surname should have at least 2 characters")
    private String surname;

    public User toUser() {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .token(UUID.randomUUID().toString())
                .build();
    }
}
