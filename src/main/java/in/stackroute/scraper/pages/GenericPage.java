package in.stackroute.scraper.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericPage {
	public WebElement element = null;

	public List<WebElement> getSource(WebDriver driver) throws InterruptedException {
		List<WebElement> elements = driver.findElements(By.className("_4ddWXP"));
		return elements;
	}
}
