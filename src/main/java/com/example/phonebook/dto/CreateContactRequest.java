package com.example.phonebook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateContactRequest(
        @NotBlank String name,
        @NotBlank String phone,
        @Email String email
) {}