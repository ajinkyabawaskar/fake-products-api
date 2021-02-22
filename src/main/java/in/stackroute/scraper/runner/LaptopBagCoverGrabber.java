package in.stackroute.scraper.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.stackroute.scraper.entity.Product;
import in.stackroute.scraper.pages.GenericPage;

public class LaptopBagCoverGrabber {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Code\\Java Workspace\\webdriver\\chromedriver88.exe");
		String base_url = "https://www.flipkart.com/laptop-accessories/laptop-bag-covers/pr?sid=6bo,ai3,nye";

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

			Product pro = new Product();

			long leftLimit = 1L;
			long rightLimit = 999998L;

			pro.setPid(leftLimit + (long) (Math.random() * (rightLimit - leftLimit)));
			pro.setCategory("Laptop Bag Covers");
			pro.setImage(img.getAttribute("src"));

			try {

				if (data.length == 6) {
					pro.setName(data[1]);
					String[] features = { "Color: " + data[2] };
					pro.setFeatures(features);
					pro.setRatings(data[3]);

					String[] prices = data[4].trim().split("₹");
					pro.setOfferedPrice(prices[1]);
					pro.setActualPrice(prices[2].trim().substring(0, 5));

				} else {
					pro.setName(data[0]);
					String[] features = { "Color: " + data[1] };
					pro.setFeatures(features);
					pro.setRatings(data[2]);

					String[] prices = data[3].trim().split("₹");

					pro.setOfferedPrice(prices[1].trim());
					pro.setActualPrice(prices[2].trim().substring(0, prices[1].trim().length()));

				}
			} catch (ArrayIndexOutOfBoundsException ee) {

			}
			products.add(pro);
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("LaptopBagCover.json"), products);
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		driver.close();

	}
}