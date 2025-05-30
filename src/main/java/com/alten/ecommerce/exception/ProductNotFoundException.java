package com.alten.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
	public ProductNotFoundException(Long id) {
        super("Produit non trouvé avec l'ID : " + id);
    }

}
