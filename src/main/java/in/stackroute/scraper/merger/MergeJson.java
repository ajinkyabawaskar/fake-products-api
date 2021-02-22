package in.stackroute.scraper.merger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.stackroute.scraper.entity.Product;

public class MergeJson {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		List<Product> datacards = new ArrayList<Product>();
		datacards = Arrays.asList(mapper.readValue(new File("DataCards.json"), Product[].class));
		int d1 = datacards.size();

		List<Product> keyboards = new ArrayList<Product>();
		keyboards = Arrays.asList(mapper.readValue(new File("KeyboardAndMouse.json"), Product[].class));
		int d2 = keyboards.size();

		List<Product> laptop = new ArrayList<Product>();
		laptop = Arrays.asList(mapper.readValue(new File("Laptop.json"), Product[].class));
		int d3 = laptop.size();

		List<Product> laptopAdapters = new ArrayList<Product>();
		laptopAdapters = Arrays.asList(mapper.readValue(new File("LaptopAdapters.json"), Product[].class));
		int d4 = laptopAdapters.size();

		List<Product> laptopBagCover = new ArrayList<Product>();
		laptopBagCover = Arrays.asList(mapper.readValue(new File("LaptopBagCover.json"), Product[].class));
		int d5 = laptopBagCover.size();

		List<Product> laptopCleaning = new ArrayList<Product>();
		laptopCleaning = Arrays.asList(mapper.readValue(new File("LaptopCleaning.json"), Product[].class));
		int d6 = laptopCleaning.size();

		List<Product> laptopSkins = new ArrayList<Product>();
		laptopSkins = Arrays.asList(mapper.readValue(new File("LaptopSkins.json"), Product[].class));
		int d7 = laptopSkins.size();

		List<Product> router = new ArrayList<Product>();
		router = Arrays.asList(mapper.readValue(new File("Router.json"), Product[].class));
		int d8 = router.size();

		List<Product> products = new ArrayList<Product>();
		products.addAll(datacards);
		products.addAll(keyboards);
		products.addAll(laptop);
		products.addAll(laptopAdapters);
		products.addAll(laptopBagCover);
		products.addAll(laptopCleaning);
		products.addAll(laptopSkins);
		products.addAll(router);

		System.out.println(d1 + d2 + d3 + d4 + d5 + d6 + d7 + d8);
		System.out.println(products.size());
		Collections.sort(products);

		try {
			mapper.writeValue(new File("products.json"), products);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
