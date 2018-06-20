package training.entity;

public class Category {
	// fields
	private Integer id;
	private String name;
	
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

	
}
