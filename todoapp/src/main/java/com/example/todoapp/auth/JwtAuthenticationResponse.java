package com.example.todoapp.auth;

public class JwtAuthenticationResponse {

    private String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    // Setter metodu ekleyebilirsiniz, gerekiyorsa
}

