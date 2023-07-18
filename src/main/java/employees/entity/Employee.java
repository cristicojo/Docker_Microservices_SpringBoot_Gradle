package employees.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;


@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "employees")
public class Employee {

	@Id
	public String _id;

	private String firstName;
	private String lastName;
	private Date dob;
	private String directManager;
	private double salary;
	private String department;
}
