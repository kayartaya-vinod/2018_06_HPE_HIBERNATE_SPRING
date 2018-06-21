package training.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@Column(name = "order_date")
	private Date orderDate = new Date();
	private String status = "PENDING";

	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private Set<LineItem> lineItems = new HashSet<>();
	
	// many-to-many association between order and products
	// one order has many products, but since there is no foreign key
	// amongst both tables, this has to be achieved by using a join table
	@ManyToMany()
	@JoinTable(
		name = "line_items", 
		joinColumns = { @JoinColumn(name = "order_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "product_id") }
	)
	private Set<Product> products;

	// generally this method is a good idea
	// helper function to achieve bi-directional association
	public void addLineItem(LineItem item) {
		lineItems.add(item);
		item.setOrder(this);
	}
}
