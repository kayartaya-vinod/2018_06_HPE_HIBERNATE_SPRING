package training.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brands")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	// many-to-one association
	// always represented by a collection
	// if you are using mappedBy, then the assumption is
	// that "brand" is a property in Product, which is mapped
	// with ManyToOne. If not use @JoinColumn to specify the same
	@OneToMany(mappedBy="brand", fetch= FetchType.EAGER)
	// @JoinColumn(name="brand_id")
	private Set<Product> products;

	public Brand() {
	}

	public Brand(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

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

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + "]";
	}
	
	

}
