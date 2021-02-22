package in.stackroute.scraper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.stackroute.scraper.entity.Product;
import in.stackroute.scraper.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping("/")
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
}
