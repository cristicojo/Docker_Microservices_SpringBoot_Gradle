package employees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import employees.entity.Employee;
import employees.service.EmployeeService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService service;


	@GetMapping(value = "/all")
	public List<Employee> findAllEmployees() {
		return service.getEmployees();
	}


	@GetMapping(value = "/employee/{id}")
	public Employee findEmployeeById(@PathVariable(value = "id") String id) {
		return service.getEmployeeById(id);
	}


	@PostMapping(value = "/employee")
	public Employee saveEmployee(@RequestBody Employee emp) {
		return service.createEmployee(emp);
	}


	@PostMapping(value = "/employees")
	public List<Employee> saveEmployeeS(@RequestBody List<Employee> employeeList) {
		return service.createEmployee_s(employeeList);
	}


	@PutMapping(value = "/employee/{id}")
	public Employee updateEmployee(@RequestBody Employee newEmp, @PathVariable(value = "id") String id) {
		return service.updateEmployeeById(id, newEmp);
	}


	@DeleteMapping(value = "/employee/{id}")
	public void deleteById(@PathVariable(value = "id") String id) {
		service.deleteEmployeeById(id);
	}


	@DeleteMapping(value = "/all")
	public void deleteAll() {
		service.deleteAllEmployees();
	}


	//the employee who has the biggest salary in the given department
	@GetMapping(value = "/max/{department}")
	public Employee findEmployeeByMaxSalaryByDepartment(@PathVariable(value = "department") String department) {
		return service.maxSalary(department);
	}


	//the manager who has the most "direct" employees coordinated by him
	@GetMapping(value = "/manager")
	public Employee getManager() {
		return service.getDirect_Manager();
	}


	//BONUS POINTS
	//load a json file
	@GetMapping(value = "/upload")
	public void uploadFile() {
		service.importJsonFile();
	}


	//paging
	@GetMapping(value = "/page={page}&size={size}")
	public Page<Employee> loadPage(@PathVariable(value = "page") int page, @PathVariable(value="size") int size) {
		return service.getPage(page,size);
	}


	//top n best paid employees in a given department
	@GetMapping(value = "/top/{department}/{n}")
	public List<Employee> topN(@PathVariable(value = "department") String department, @PathVariable(value="n") int n) {
		return service.topNBest(department,n);
	}


	//management tree: from the top CEO to the lowest employee
	@GetMapping(value = "/tree")
	public List<Employee> tree() {
		return service.managementTree();
	}

}
