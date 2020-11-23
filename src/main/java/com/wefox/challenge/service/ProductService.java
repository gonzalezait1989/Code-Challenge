package com.wefox.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wefox.challenge.model.Product;
import com.wefox.challenge.repository.ProductRespository;
import com.wefox.challenge.vo.ProductVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

  @Autowired
  private ProductRespository productRespository;

  public List<ProductVO> findAll() {
    List<ProductVO> products = new ArrayList<ProductVO>();
    productRespository.findAll().iterator().forEachRemaining(p -> products.add(getProductVO(p)));
    return products;
  }

  public Optional<ProductVO> findById(Long id) {
    return productRespository.findById(id).map(p -> getProductVO(p));
  }

  public ProductVO save(ProductVO product) {
    return getProductVO(productRespository.save(this.getProduct(product)));
  }

  public void deleteById(Long id) {
    productRespository.deleteById(id);
  }

  /**
   * Transform Product to ProductVO
   * 
   * @param product
   * @return
   */
  private ProductVO getProductVO(Product product) {
    return product != null
        ? ProductVO.builder().created(product.getCreated()).updated(product.getUpdated())
            .id(product.getId()).description(product.getDescription()).name(product.getName())
            .price(product.getPrice()).stock(product.getStock()).build()
        : ProductVO.builder().build();
  }

  /**
   * Transform Product to ProductVO
   * 
   * @param product
   * @return
   */
  private Product getProduct(ProductVO product) {
    return product != null
        ? Product.builder().id(product.getId()).description(product.getDescription())
            .name(product.getName()).price(product.getPrice()).stock(product.getStock()).build()
        : Product.builder().build();
  }
}
