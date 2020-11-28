package com.aitorgonzalez.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aitorgonzalez.challenge.repository.ProductRespository;
import com.aitorgonzalez.challenge.service.ProductService;
import com.aitorgonzalez.challenge.vo.ProductVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRespository productRespository;

	@Override
	public void deleteById(Long id) {
		productRespository.deleteById(id);
	}

	@Override
	public List<ProductVO> findAll() {
		List<ProductVO> products = new ArrayList<>();
		productRespository.findAll().iterator().forEachRemaining(p -> products.add(getProductVO(p)));
		return products;
	}

	@Override
	public Optional<ProductVO> findById(Long id) {
		return productRespository.findById(id).map(this::getProductVO);
	}

	@Override
	public Optional<ProductVO> findByName(String name) {
		return productRespository.findByName(name).map(this::getProductVO);
	}

	@Override
	public ProductVO save(ProductVO product) {
		return getProductVO(productRespository.save(this.getProduct(product)));
	}
}
