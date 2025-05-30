package com.alten.ecommerce.service;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.repository.WishlistRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.User;
import com.alten.ecommerce.model.Wishlist;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    
    private final UserRepository userRepository;

    private final ProductRepository productRepository;
	
	public Wishlist getOrCreateWishlist(String email) {
		User user = userRepository.findByEmail(email).orElseThrow();
		return wishlistRepository.findByUser(user)
				.orElseGet(()->{
					Wishlist newWishlist = new Wishlist();
					newWishlist.setUser(user);
					return wishlistRepository.save(newWishlist);
				});
	}
	
	public Wishlist addProduct(String email, Long productId) {
		Wishlist wishlist = getOrCreateWishlist(email);
		Product product = productRepository.findById(productId).orElseThrow();
		
		if(!wishlist.getProducts().contains(product)) {
			wishlist.getProducts().add(product);
		}
		return wishlistRepository.save(wishlist);
	}
	
	public Wishlist removeproduct(String email,Long productId) {
		Wishlist wishlist = getOrCreateWishlist(email);
		wishlist.getProducts().removeIf(p -> p.getId().equals(productId));
		return wishlistRepository.save(wishlist);
	}

}
