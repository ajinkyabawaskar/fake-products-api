package in.stackroute.scraper.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LaptopPage {

	public WebElement element = null;

	public List<WebElement> getSource(WebDriver driver, int upto) {
		List<WebElement> products = new ArrayList<WebElement>();
		for (int i = 2; i < upto; i++) {
			String path = "/html/body/div/div/div[3]/div[1]/div[2]/div[" + i + "]";
			WebElement element = driver.findElement(By.xpath(path));
			products.add(element);
		}
		return products;
	}

}