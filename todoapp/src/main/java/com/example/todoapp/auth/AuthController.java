package com.example.todoapp.auth;

import com.example.todoapp.UserData.UserData;
import com.example.todoapp.UserData.UserRegistrationDto;
import com.example.todoapp.UserData.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService, JwtTokenProvider tokenProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }


    private final JwtTokenProvider tokenProvider;

    @PostMapping("/register")
        public ResponseEntity<UserData> createUser(@RequestBody UserRegistrationDto registrationDto) {
        UserData createdUser = userService.createUser(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Kullanıcı giriş isteğini işleyen kodu buraya ekleyin

        // Örnek kod:
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        // Token'i içeren bir response döndürün
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
}

