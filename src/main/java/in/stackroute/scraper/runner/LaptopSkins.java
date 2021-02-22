package in.stackroute.scraper.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.stackroute.scraper.entity.Product;
import in.stackroute.scraper.pages.GenericPage;

public class LaptopSkins {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Code\\Java Workspace\\webdriver\\chromedriver88.exe");
		String base_url = "https://www.flipkart.com/laptop-accessories/laptop-skins-decals/pr?sid=6bo,ai3,zvm";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		Thread.sleep(600);
		driver.get(base_url);
		GenericPage page = new GenericPage();
		List<Product> products = new ArrayList<Product>();

		List<WebElement> source_input = page.getSource(driver);
		for (WebElement e : source_input) {
			WebElement img = e.findElement(By.tagName("img"));
			String details = e.getText();
			String[] data = details.split("\\r?\\n");

//			for (int i = 0; i < data.length; i++) {
//				System.out.println(i + " " + data[i]);
//			}
			Product pro = new Product();

			long leftLimit = 1L;
			long rightLimit = 999998L;

			pro.setPid(leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
			pro.setCategory("Laptop Skins");
			pro.setImage(img.getAttribute("src"));

			try {

				if (data.length == 5) {
					pro.setName(data[1]);
					String[] features = { " " + data[0] };
					pro.setFeatures(features);
					pro.setRatings(data[2]);

					String[] prices = data[3].trim().split("₹");
					pro.setOfferedPrice(prices[1]);
					pro.setActualPrice(prices[2].trim().substring(0, 5));

				} else {
					pro.setName(data[0]);
					String[] features = { "" + data[3] };
					pro.setFeatures(features);
					pro.setRatings(data[1]);

					String[] prices = data[2].replace("â‚¹", "").trim().split("â‚¹");

					pro.setOfferedPrice(prices[1].trim());
					pro.setActualPrice(prices[2].trim().substring(0, prices[1].trim().length()));

				}
			} catch (ArrayIndexOutOfBoundsException ee) {
				int leftLimit2 = 170;
				int rightLimit2 = 500;
				int prr = leftLimit2 + (int) (new Random().nextFloat() * (rightLimit2 - leftLimit2));
				pro.setOfferedPrice("" + prr);
				pro.setActualPrice("" + (prr + 50 * (int) (new Random().nextFloat() * (75 - 25))));

			}
			products.add(pro);
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("LaptopSkins.json"), products);
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		driver.close();

	}
}