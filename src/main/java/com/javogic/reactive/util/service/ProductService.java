package com.javogic.reactive.util.service;

import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.javogic.reactive.dto.ProductDTO;
import com.javogic.reactive.repository.ProductRepository;
import com.javogic.reactive.util.ProductUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Flux<ProductDTO> findAll() {
		return productRepository.findAll().map(ProductUtil::entityToDto);
	}

	public Mono<ProductDTO> findById(String id) {
		return productRepository.findById(id).map(ProductUtil::entityToDto);
	}

	public Flux<ProductDTO> findByPriceInRange(double min, double max) {
		return productRepository.findByPriceBetween(Range.closed(min, max));
	}

	public Mono<ProductDTO> save(Mono<ProductDTO> productDTO) {
		return productDTO.map(ProductUtil::dtoTOEntity).flatMap(productRepository::insert)
				.map(ProductUtil::entityToDto);
	}

	public Mono<ProductDTO> update(Mono<ProductDTO> productDTO, String id) {
		return productRepository.findById(id)
				.flatMap(p -> productDTO.map(ProductUtil::dtoTOEntity).doOnNext(e -> e.setId(id)))
				.flatMap(productRepository::insert).map(ProductUtil::entityToDto);

	}
}
