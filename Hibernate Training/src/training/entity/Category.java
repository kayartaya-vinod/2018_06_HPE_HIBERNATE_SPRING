package training.entity;

import java.util.Set;

public class Category {
	// fields
	private Integer id;
	private String name;
	
	// one-to-many association
	private Set<Product> products;
	
	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	// readable property (accessor) called "id"
	public Integer getId() {
		return id;
	}

	// writable property (mutator) called "id"
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
}
