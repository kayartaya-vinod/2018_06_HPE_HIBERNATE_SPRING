package training.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
// OPTION 1: one table for each of the subclasses
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

// OPTION 2: a single table having a union of columns from all sub classes +
// discriminator
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "person_type", discriminatorType =
// DiscriminatorType.STRING)

// OPTION 3: Normalized version of tables; Parent / Child tables will be created
// with foreign key relationships
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
	@Id
	private Integer id;
	private String name;
	private String city;

}
