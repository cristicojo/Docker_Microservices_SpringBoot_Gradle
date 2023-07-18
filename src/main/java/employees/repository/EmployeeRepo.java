package employees.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import employees.entity.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee,String>, PagingAndSortingRepository<Employee, String> {

	List<Employee> findByDepartment(String department);

}
