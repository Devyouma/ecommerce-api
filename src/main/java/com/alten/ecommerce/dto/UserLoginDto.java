package com.alten.ecommerce.dto;

import jakarta.validation.constraints.*;

public record UserLoginDto(
		@NotBlank(message = "L'email est obligatoire")
	    @Email(message = "L'email doit être valide")
	    String email,
	    
	    @NotBlank(message = "Le mot de passe est obligatoire")
	    String password
		) {}
