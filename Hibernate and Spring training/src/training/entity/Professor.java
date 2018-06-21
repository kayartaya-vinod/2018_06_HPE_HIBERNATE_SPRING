package training.entity;

//import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
// @DiscriminatorValue("PROFESSOR")
public class Professor extends Person {
	private String department;
	private String email;

	public Professor(Integer id, String name, String city, String department, String email) {
		super(id, name, city);
		this.department = department;
		this.email = email;
	}

}
