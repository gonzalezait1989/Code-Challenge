package com.wefox.challenge.service;

import java.util.List;
import java.util.Optional;

import com.wefox.challenge.vo.ProductVO;

/**
 * Service to manage products.
 * 
 * @author aitor
 */
public interface ProductService {

  /**
   * Gets all the Products.
   * 
   * @return a list of Products.
   */
  public List<ProductVO> findAll();

  /**
   * Finds an ProductVO by ID.
   * 
   * @param id the Id of the ProductVO.
   * @return an Optional of the ProductVO.
   */
  public Optional<ProductVO> findById(Long id);

  /**
   * Finds an ProductVO by ID.
   * 
   * @param id the Id of the ProductVO.
   * @return an Optional of the ProductVO.
   */
  public ProductVO save(ProductVO product);

  /**
   * Deletes an Product by it's Id.
   * 
   * @param id the id of the Product.
   */
  public void deleteById(Long id);
}
