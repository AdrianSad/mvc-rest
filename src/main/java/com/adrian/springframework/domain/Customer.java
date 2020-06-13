package com.adrian.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Customer {

    @Id
    private String id;

    @NotNull(message = "First Name cannot be empty")
    @NotEmpty
    private String firstName;

    @NotNull(message = "Last Name cannot be empty")
    @NotEmpty
    private String lastName;

    private int age;
}
