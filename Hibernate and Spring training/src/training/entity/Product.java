package training.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Cache(region="region1", usage=CacheConcurrencyStrategy.READ_ONLY)
public class Product {

	// primary/candidate key field
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	// scalar fields
	private String name;
	private String description;
	@Column(name = "quantity_per_unit")
	private String quantityPerUnit;
	@Column(name = "unit_price")
	private Double unitPrice;
	private String picture;
	private Double discount;

	// assoication fields
	// foreign key --> ManyToOne
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private Brand brand;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	// one product has been referenced by multiple orders
	// one product --> many orders
	// since there is no foreign key relationship between products and orders
	// we must use the many-to-many association using line_items as a join table
	@ManyToMany()
	@JoinTable(name="line_items",
		joinColumns = @JoinColumn(name="product_id"),
		inverseJoinColumns = @JoinColumn(name="order_id")
	)
	private Set<Order> orders;

}









