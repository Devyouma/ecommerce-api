package com.alten.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alten.ecommerce.dto.ProductDTOs.CreateProduct;
import com.alten.ecommerce.dto.ProductDTOs.UpdateProduct;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@Tag(name = "Produits", description = "Opérations liées aux produits")
public class ProductController {

	private final ProductService productService;
	
	@GetMapping
	@Operation(summary="Retrieve all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
	
	@GetMapping("/{id}")
	@Operation(summary="Retrieve details for product")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Operation(summary = "Remove product")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create a new product ")
    public Product createProduct(@RequestBody @Valid CreateProduct dto) {
        return productService.createProduct(dto);
    }
	
	@PatchMapping("/{id}")
	@Operation(summary = "Update details of product")
	public Product updateProduct(@PathVariable Long id, @RequestBody UpdateProduct dto) {
        return productService.updateProductPartial(id, dto);
    }
}
