package com.alten.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.alten.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	boolean existsByCode(String code);
}
