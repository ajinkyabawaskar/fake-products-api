package in.stackroute.scraper.service;

import java.util.List;

import in.stackroute.scraper.entity.Product;

public interface ProductService {

	List<Product> findAll();

	List<Product> findAllByName(String name);

	List<Product> findAllByCategory(String name);

	Product findByName(String name);

	Product findById(Long id);

	void saveAll(List<Product> products);
}
