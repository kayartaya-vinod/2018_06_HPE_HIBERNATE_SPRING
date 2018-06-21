package training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
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
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;


}
