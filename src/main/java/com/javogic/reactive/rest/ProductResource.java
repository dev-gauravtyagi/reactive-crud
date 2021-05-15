package com.javogic.reactive.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javogic.reactive.dto.ProductDTO;
import com.javogic.reactive.util.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ProductResource {

	private final ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public Flux<ProductDTO> findAllProducts() {
		return productService.findAll();
	}

	@GetMapping("/products/{id}")
	public Mono<ProductDTO> findProductBuId(@PathVariable(name = "id") String productId) {
		return productService.findById(productId);
	}

	@GetMapping("/products-by-price")
	public Flux<ProductDTO> findAllProductsByPriceRange(@RequestParam(name = "min") double minPrice, double maxPrice) {
		return productService.findByPriceInRange(minPrice, maxPrice);
	}

	@PostMapping("/products")
	public Mono<ProductDTO> findAllProductsByPriceRange(@RequestBody Mono<ProductDTO> productDTO) {
		return productService.save(productDTO);
	}

	@PutMapping("/products/{id}")
	public Mono<ProductDTO> findAllProductsByPriceRange(@RequestBody Mono<ProductDTO> productDTO,
			@PathVariable(name = "id") String productId) {
		return productService.update(productDTO, productId);
	}
}
