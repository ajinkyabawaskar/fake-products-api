package in.stackroute.scraper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.stackroute.scraper.entity.Product;
import in.stackroute.scraper.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> findAllByName(String name) {
		return productRepository.findAllByName(name);
	}

	@Override
	public List<Product> findAllByCategory(String name) {
		return productRepository.findAllByCategory(name);
	}

	@Override
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public void saveAll(List<Product> products) {
		productRepository.saveAll(products);
	}
}
