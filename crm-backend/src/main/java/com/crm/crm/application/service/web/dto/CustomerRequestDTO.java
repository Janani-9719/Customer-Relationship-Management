package com.crm.crm.application.service.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO(
            @NotBlank String firstName,
            String lastName,
            @NotBlank @Email String email,
            String phoneNumber,
            String address,
            String city,
            String state,
            String country
) {
}
