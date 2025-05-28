package com.alten.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alten.ecommerce.dto.ProductDTOs.CreateProduct;
import com.alten.ecommerce.dto.ProductDTOs.UpdateProduct;
import com.alten.ecommerce.exception.ProductNotFoundException;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
	
	public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
	
	@Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
	
	@Transactional
    public Product createProduct(CreateProduct dto) {
        if (productRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Product code already exists");
        }
        
        Product product = new Product();
        mapCreateDtoToEntity(dto, product);
        return productRepository.save(product);
    }
	private void mapCreateDtoToEntity(CreateProduct dto, Product entity) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setCategory(dto.getCategory());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setInternalReference(dto.getInternalReference());
        entity.setShellId(dto.getShellId());
        entity.setInventoryStatus(dto.getInventoryStatus());
        entity.setRating(dto.getRating());
    }

	@Transactional
    public Product updateProductPartial(Long id, UpdateProduct dto) {
        Product product = getProductById(id);
        
        if (dto.getName() != null) product.setName(dto.getName());
        if (dto.getDescription() != null) product.setDescription(dto.getDescription());
        if (dto.getImage() != null) product.setImage(dto.getImage());
        if (dto.getCategory() != null) product.setCategory(dto.getCategory());
        if (dto.getPrice() != null) product.setPrice(dto.getPrice());
        if (dto.getQuantity() != null) product.setQuantity(dto.getQuantity());
        if (dto.getInternalReference() != null) product.setInternalReference(dto.getInternalReference());
        if (dto.getShellId() != null) product.setShellId(dto.getShellId());
        if (dto.getInventoryStatus() != null) product.setInventoryStatus(dto.getInventoryStatus());
        if (dto.getRating() != null) product.setRating(dto.getRating());
        
        return productRepository.save(product);
    }
}
