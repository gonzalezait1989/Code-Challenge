package com.aitorgonzalez.challenge.service;

import java.util.List;
import java.util.Optional;

import com.aitorgonzalez.challenge.model.Product;
import com.aitorgonzalez.challenge.vo.ProductVO;

/**
 * Service to manage products.
 * 
 * @author aitor
 */
public interface ProductService {

	/**
	 * Deletes an Product by it's Id.
	 * 
	 * @param id the id of the Product.
	 */
	public void deleteById(Long id);

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
	 * Finds an Product VO by name.
	 * 
	 * @param name the name of the ProductVO.
	 * @return an Optional of the ProductVO.
	 */
	public Optional<ProductVO> findByName(String name);

	/**
	 * Transform Product to ProductVO
	 * 
	 * @param product
	 * @return
	 */
	default Product getProduct(ProductVO product) {
		return product != null
				? Product.builder().id(product.getId()).description(product.getDescription()).name(product.getName())
						.price(product.getPrice()).stock(product.getStock()).build()
				: Product.builder().build();
	}

	/**
	 * Transform Product to ProductVO
	 * 
	 * @param product
	 * @return
	 */
	default ProductVO getProductVO(Product product) {
		return product != null
				? ProductVO.builder().created(product.getCreated()).updated(product.getUpdated()).id(product.getId())
						.description(product.getDescription()).name(product.getName()).price(product.getPrice())
						.stock(product.getStock()).build()
				: ProductVO.builder().build();
	}

	/**
	 * Finds an ProductVO by ID.
	 * 
	 * @param id the Id of the ProductVO.
	 * @return an Optional of the ProductVO.
	 */
	public ProductVO save(ProductVO product);
}
