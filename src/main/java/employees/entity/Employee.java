package employees.entity;

import java.time.LocalDate;

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
	public String id;

	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String directManager;
	private Double salary;
	private String department;
}
