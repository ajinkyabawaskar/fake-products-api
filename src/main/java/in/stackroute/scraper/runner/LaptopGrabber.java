package in.stackroute.scraper.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.stackroute.scraper.entity.Product;
import in.stackroute.scraper.pages.LaptopPage;

public class LaptopGrabber {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Code\\Java Workspace\\webdriver\\chromedriver88.exe");
		String base_url = "https://www.flipkart.com/laptops/pr?sid=6bo,b5g";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get(base_url);
		LaptopPage page = new LaptopPage();

		List<Product> products = new ArrayList<Product>();

		List<WebElement> source_input = page.getSource(driver, 20);
		for (WebElement e : source_input) {
			WebElement img = e.findElement(By.tagName("img"));
			String details = e.getText();
			String[] data = details.split("\\r?\\n");

			Product pro = new Product();
			pro.setCategory("laptops");
			pro.setPid(new Random().nextLong());
			if (!data[1].contains("Add to compare")) {
				pro.setName(data[1]);
				pro.setRatings(data[2]);
				String[] features = Arrays.copyOfRange(data, 3, 8);
				pro.setFeatures(features);
			} else {
				pro.setName(data[2]);
				pro.setRatings(data[3]);
				String[] features = Arrays.copyOfRange(data, 4, 9);
				pro.setFeatures(features);
			}

			String[] price = details.replace("\\r?\\n", " ").split("₹");
			pro.setOfferedPrice(price[1].replace("\n", ""));
			pro.setActualPrice(price[2].substring(0, price[1].length() - 1));

			WebDriverWait wait = new WebDriverWait(driver, 5);
			img = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class='_396cs4 _3exPp9']")));

			pro.setImage(img.getAttribute("src"));
			products.add(pro);

		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("Laptop.json"), products);
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		driver.close();

	}
}