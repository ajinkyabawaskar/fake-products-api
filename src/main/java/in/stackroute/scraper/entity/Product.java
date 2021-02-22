package in.stackroute.scraper.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Product implements Comparable<Product> {
	@Id
	private Long pid;
	private String name;
	private String ratings;
	private String category;
	private String[] features;
	private String offeredPrice;
	private String actualPrice;
	private String image;

	@Override
	public int compareTo(Product product) {
		if (getName() == null || product.getName() == null) {
			return 0;
		}
		return getPid().compareTo(product.getPid());
	}
}
