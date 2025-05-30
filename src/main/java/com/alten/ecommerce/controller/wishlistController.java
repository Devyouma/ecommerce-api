package com.alten.ecommerce.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.alten.ecommerce.model.Wishlist;
import com.alten.ecommerce.service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class wishlistController {
	
	private final WishlistService wishlistService;
	
	@GetMapping
	public ResponseEntity<Wishlist> getwishlist(Authentication auth){
		return ResponseEntity.ok(wishlistService.getOrCreateWishlist(auth.getName()));
	}

	@DeleteMapping("/remove/{productId}")
	public ResponseEntity<Wishlist> removeFromWishlist(Authentication auth,@PathVariable Long productId){
		return ResponseEntity.ok(wishlistService.removeproduct(auth.getName(), productId));
	}
	
	@PostMapping("/add")
	public ResponseEntity<Wishlist> addToWishlist(Authentication auth,@RequestBody Map<String, Long> body){
		Long productId = body.get("productId");
		return ResponseEntity.ok(wishlistService.addProduct(auth.getName(), productId) );
	}
}
