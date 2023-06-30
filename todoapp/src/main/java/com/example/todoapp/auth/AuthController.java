package com.example.todoapp.auth;

import com.example.todoapp.UserData.UserData;
import com.example.todoapp.UserData.UserRegistrationDto;
import com.example.todoapp.UserData.UserRepository;
import com.example.todoapp.UserData.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService, JwtTokenProvider tokenProvider,
                          UserRepository userRepository) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }


    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/register")
        public ResponseEntity<UserData> createUser(@RequestBody UserRegistrationDto registrationDto) {
        UserData createdUser = userService.createUser(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @GetMapping("/forgottenpassword")
    public ResponseEntity<String> passwordforget(@RequestBody PasswordForgetDto dto) {
        if (userRepository.findUserByUsernameAndEmail(dto.getUsername(), dto.getEmail()) != null) {

            UserData userData = userRepository.findUserByUsernameAndEmail(dto.getUsername(), dto.getEmail());
            return ResponseEntity.ok("Kullanıcı bilgileriniz, kullanıcı adı: " + userData.getUsername() + " " + " şifreniz: " + userData.getPassword());
        }
        return ResponseEntity.ok("Yanlis bilgi girdiniz");
    }

    @PutMapping("/setrole")
    public ResponseEntity<String> roleSetting(@RequestBody RoleSetDto dto){
        UserData userData = userRepository.findUserByUsername(dto.getUsername());
        Set<String> stringSet = new HashSet<>();
        stringSet.add(dto.getRoles());

        userData.setRoles(stringSet);
        userRepository.save(userData);
        return ResponseEntity.ok(userData.toString());
    }

}

