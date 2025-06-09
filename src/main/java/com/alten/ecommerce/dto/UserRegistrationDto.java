package com.alten.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationDto (
	@NotBlank(message = "Le nom d'utilisateur est obligatoire")
    String username,
    
    @NotBlank(message = "Le prénom est obligatoire")
    String firstname,
    
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    String email,
    
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    String password
    ){}
