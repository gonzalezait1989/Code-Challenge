package com.wefox.challenge.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wefox.challenge.service.ProductService;
import com.wefox.challenge.vo.ProductVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductApi {

  @Autowired
  private final ProductService productService;

  @GetMapping
  public ResponseEntity<List<ProductVO>> findAll() {
    return ResponseEntity.ok(productService.findAll());
  }

  @PostMapping
  public ResponseEntity<ProductVO> create(@Valid @RequestBody ProductVO product) {
    return ResponseEntity.ok(productService.save(product));
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
  public ResponseEntity<ProductVO> update(@PathVariable Long id,
      @Valid @RequestBody ProductVO product) {
    if (!productService.findById(id).isPresent()) {
      log.error("Id " + id + " is not existed");
      ResponseEntity.badRequest().build();
    }

    return ResponseEntity.ok(productService.save(product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    if (!productService.findById(id).isPresent()) {
      log.error("Id " + id + " is not existed");
      ResponseEntity.badRequest().build();
    }

    productService.deleteById(id);

    return ResponseEntity.ok().build();
  }
}
