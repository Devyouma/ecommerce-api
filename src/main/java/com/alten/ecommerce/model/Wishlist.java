package com.alten.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Wishlist {

	@Id @GeneratedValue
	private Long id;
	
	@OneToOne
	private User user;
	
	@ManyToMany
	private List<Product> products = new ArrayList<>();
}
