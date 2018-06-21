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
// @DiscriminatorValue("STUDENT")
public class Student extends Person {
	private String subject;

	public Student(Integer id, String name, String city, String subject) {
		super(id, name, city);
		this.subject = subject;
	}

}
