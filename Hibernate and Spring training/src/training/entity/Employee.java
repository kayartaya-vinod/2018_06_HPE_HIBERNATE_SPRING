package training.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double salary;

	@OneToOne(mappedBy = "ownedBy", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private Laptop laptop;

	public Employee(String name, Double salary) {
		this.name = name;
		this.salary = salary;
	}

}
