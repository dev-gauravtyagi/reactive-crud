package com.javogic.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "products")
public class Product {

	@Id
	private String id;
	private String name;
	private int quantity;
	private double price;
}
