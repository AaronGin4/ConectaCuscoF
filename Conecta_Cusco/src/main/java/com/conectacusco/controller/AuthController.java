package com.conectacusco.controller;

import com.conectacusco.dto.JwtResponse;
import com.conectacusco.dto.LoginRequest;
import com.conectacusco.dto.RegisterRequest;
import com.conectacusco.model.Usuario;
import com.conectacusco.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) // Permitir CORS desde Angular
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest signUpRequest) {
        try {
            Usuario newUser = authService.registerUser(signUpRequest);
            return ResponseEntity.ok("Usuario registrado exitosamente!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}