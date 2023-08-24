package employees.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import employees.entity.Employee;
import employees.service.EmployeeService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {

	private final EmployeeService service;


	@GetMapping(value = "/find/all")
	public List<Employee> findAll() {
		return service.getAll();
	}


	@GetMapping(value = "/employee/{id}")
	public Employee findById(@PathVariable(value = "id") String id) {
		return service.getById(id);
	}


	@PostMapping(value = "/create")
	public Employee save(@RequestBody Employee emp) {
		return service.create(emp);
	}


	@PostMapping(value = "/create/all")
	public List<Employee> saveAll(@RequestBody List<Employee> employeeList) {
		return service.createAll(employeeList);
	}


	@PutMapping(value = "/employee/{id}")
	public Employee update(@RequestBody Employee newEmp, @PathVariable(value = "id") String id) {
		return service.updateById(id, newEmp);
	}


	@DeleteMapping(value = "/employee/{id}")
	public void deleteById(@PathVariable(value = "id") String id) {
		service.remove(id);
	}


	@DeleteMapping(value = "/remove/all")
	public void deleteAll() {
		service.removeAll();
	}


	//the employee who has the biggest salary in the given department
	@GetMapping(value = "/max/{department}")
	public Employee findByByDepartmentMaxSalary(@PathVariable(value = "department") String department) {
		return service.maxSalary(department);
	}


	//the manager who has the most "direct" employees coordinated by him
	@GetMapping(value = "/manager")
	public Employee getManager() {
		return service.getDirectManager();
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
		return service.topBestPaid(department,n);
	}


	//management tree: from the top CEO to the lowest employee
	@GetMapping(value = "/tree")
	public List<Employee> tree() {
		return service.managementTree();
	}

}

