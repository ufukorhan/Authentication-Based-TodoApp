package com.hepsiemlak.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.persistence.Id;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    @JsonIgnore
    private String password;
    private String token;
    private Instant createDate;
    private Instant updateDate;
}
