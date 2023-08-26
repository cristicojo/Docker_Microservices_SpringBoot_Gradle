package employee.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import employee.entity.Employee;

@Repository
public interface EmployeeRepository
		extends MongoRepository<Employee,String>, PagingAndSortingRepository<Employee, String> {

	List<Employee> findByDepartment(String department);

}
