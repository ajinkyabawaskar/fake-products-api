package in.stackroute.scraper.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import in.stackroute.scraper.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	List<Product> products = new ArrayList<Product>();

	@Override
	public List<Product> findAll() {
		return products;
	}

	@Override
	public List<Product> findAllByName(String name) {
		List<Product> productsByName = new ArrayList<Product>();
		for (Product p : products) {
			if (p.getName().contains(name) || p.getName().equalsIgnoreCase(name)) {
				productsByName.add(p);
			}
		}
		return productsByName;
	}

	@Override
	public List<Product> findAllByCategory(String name) {
		List<Product> productsByCategory = new ArrayList<Product>();
		for (Product p : products) {
			if (p.getCategory().contains(name) || p.getCategory().equalsIgnoreCase(name)) {
				productsByCategory.add(p);
			}
		}
		return productsByCategory;
	}

	@Override
	public Product findByName(String name) {
		Product productByName = null;
		for (Product p : products) {
			if (p.getName().contains(name) || p.getName().equalsIgnoreCase(name)) {
				productByName = p;
				break;
			}
		}
		return productByName;
	}

	@Override
	public Product findById(Long id) {
		Product productById = null;
		for (Product p : products) {
			if (p.getPid() == id) {
				productById = p;
				break;
			}
		}
		return productById;
	}

	@Override
	public void saveAll(List<Product> products) {
		this.products.addAll(products);
	}

}
