package com.frailty.backend.dto;

public record RegistrationRequest(String firstName, String lastName, String email, String password) {
}
