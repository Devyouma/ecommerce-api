package com.alten.ecommerce.dto;

import com.alten.ecommerce.model.Product.InventoryStatus;

import jakarta.validation.constraints.*;
import lombok.Data;

public class ProductDTOs {
	@Data
    public static class CreateProduct {
        
        private String code;        
        
        private String name;
        
        private String description;
        private String image;
        private String category;
        
        private Double price;
        
        private Integer quantity;
        
        private String internalReference;
        private Long shellId;
        private InventoryStatus inventoryStatus;
        
        private Double rating;
    }
	
	@Data
	public static class UpdateProduct {
		private String name;
        private String description;
        private String image;
        private String category;
        private Double price;
        private Integer quantity;
        private String internalReference;
        private Long shellId;
        private InventoryStatus inventoryStatus;
        private Double rating;
	}
}
