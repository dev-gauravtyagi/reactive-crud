package com.javogic.reactive.util;

import org.springframework.beans.BeanUtils;

import com.javogic.reactive.dto.ProductDTO;
import com.javogic.reactive.entity.Product;

public class ProductUtil {

	public static ProductDTO entityToDto(Product product) {
		ProductDTO productDTO = new ProductDTO();
		BeanUtils.copyProperties(product, productDTO);
		return productDTO;
	}

	public static Product dtoTOEntity(ProductDTO productDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productDTO, product);
		return product;
	}
}
