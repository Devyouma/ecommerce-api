package com.alten.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.alten.ecommerce.dto.UserLoginDto;
import com.alten.ecommerce.dto.UserRegistrationDto;
import com.alten.ecommerce.model.User;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.service.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "Authentification", description = "Opérations liées a l'authentification")	
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping("/account")
    @Operation(summary="Permet de créer un nouveau compte pour un utilisateur")
    public ResponseEntity<?> register(@Validated @RequestBody UserRegistrationDto dto,
    		BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		return handleValidationErrors(bindingResult);
    	}
    	if(userRepository.existsByEmail(dto.email())) {
    		return ResponseEntity
    				.status(HttpStatus.CONFLICT)
    				.body(Map.of("error","cet Email existe deja"));    		
    	}
    	
        User user = new User();
        user.setUsername(dto.username());
        user.setFirstname(dto.firstname());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/token")
    @Operation(summary="Permet de se connecter à l'application")
    public ResponseEntity<?> login(@Validated @RequestBody UserLoginDto dto,BindingResult bindingResult) {
        String email = dto.email();
        String password = dto.password();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(Map.of("token", token));
    }
    
    private ResponseEntity<Map<String, String>> handleValidationErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

}
