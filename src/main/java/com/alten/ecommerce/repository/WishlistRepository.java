package com.alten.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.ecommerce.model.User;
import com.alten.ecommerce.model.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long>{
	Optional<Wishlist> findByUser(User user);
}
