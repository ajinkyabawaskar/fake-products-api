package in.stackroute.scraper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.stackroute.scraper.entity.Product;
import in.stackroute.scraper.repository.ProductRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(ApplicationArguments args) throws JsonParseException, JsonMappingException, IOException {
		List<Product> products = productRepository.findAll();
		if (products.size() == 0) {
			ObjectMapper mapper = new ObjectMapper();
			products = Arrays.asList(mapper.readValue(new File("products.json"), Product[].class));
			productRepository.saveAll(products);
		}

	}
}
