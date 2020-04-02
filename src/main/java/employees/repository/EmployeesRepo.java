package employees.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import employees.modelMongo.Employees;

@Repository
public interface EmployeesRepo extends MongoRepository<Employees,String>, PagingAndSortingRepository<Employees, String> {

	List<Employees> findByDepartment(String department);

}
