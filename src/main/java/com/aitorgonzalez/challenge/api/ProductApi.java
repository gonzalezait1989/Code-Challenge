package com.aitorgonzalez.challenge.api;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aitorgonzalez.challenge.exceptions.InvalidRequestException;
import com.aitorgonzalez.challenge.exceptions.ResourceNotFoundException;
import com.aitorgonzalez.challenge.service.ProductService;
import com.aitorgonzalez.challenge.vo.ProductVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductApi {

	@Autowired
	private final ProductService productService;

	@PostMapping
	public ResponseEntity<ProductVO> create(@Valid @RequestBody ProductVO product, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException(bindingResult);
		}
		if (productService.findByName(product.getName()).isPresent()) {
			bindingResult.rejectValue("product", "DUPLICATED", "product name already exists");
			throw new InvalidRequestException(bindingResult);
		}
		return ResponseEntity.ok(productService.save(product));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return productService.findById(id).map(productFromDB -> {
			productService.deleteById(id);
			return ResponseEntity.noContent().build();
		}).orElseThrow(ResourceNotFoundException::new);
	}

	@GetMapping
	public ResponseEntity<HashMap> findAll() {
		List<ProductVO> products = productService.findAll();
		return ResponseEntity.ok(new HashMap<String, Object>() {
			{
				put("products", products);
			}
		});
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductVO> findById(@PathVariable Long id) {
		Optional<ProductVO> product = productService.findById(id);
		if (!product.isPresent()) {
			log.error("Id " + id + " is not existed");
			ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(product.get());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductVO> update(@PathVariable Long id, @Valid @RequestBody ProductVO product) {
		return productService.findById(id).map(productFromDB -> {
			ProductVO updatedProduct = productService.save(productFromDB);
			return ResponseEntity.ok(updatedProduct);
		}).orElseThrow(ResourceNotFoundException::new);
	}
}
