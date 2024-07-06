package com.hepsiemlak.todo.model.dto;

import com.hepsiemlak.todo.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class UserResponse {
    private String Id;
    private String email;
    private String name;
    private String surname;
    private Instant createDate;
    private Instant updateDate;
}
