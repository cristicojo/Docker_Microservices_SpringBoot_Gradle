package employees.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import employees.exception.DepartmentNotFoundException;
import employees.exception.EmployeesNotFoundException;
import employees.modelMongo.Employees;
import employees.repository.EmployeesRepo;

@Service
public class EmployeesService {

	@Autowired
	private EmployeesRepo repo;

	@Autowired
	private MongoTemplate mongoTemplate;


	public List<Employees> getEmployees() {
		return repo.findAll();
	}

	public Employees getEmployeeById(String id) {
		return repo.findById(id).orElseThrow(() -> new EmployeesNotFoundException("Could not found an employee with id: " + id));
	}


	public Employees createEmployee(Employees newEmp) {
		return repo.save(newEmp);
	}


	public List<Employees> createEmployee_s(List<Employees> employeesList) {
		return repo.saveAll(employeesList);
	}


	public Employees updateEmployeeById(String id, Employees newEmp) {

		Optional<Employees> optional = repo.findById(id);

		if (!(optional.isPresent())) {
			throw new EmployeesNotFoundException("Could not found an employee with id: " + id);
		}

		Employees oldEmp = optional.get();
		oldEmp.setFirst_name(newEmp.getFirst_name());
		oldEmp.setLast_name(newEmp.getLast_name());
		oldEmp.setDob(newEmp.getDob());
		oldEmp.setDirect_manager(newEmp.getDirect_manager());
		oldEmp.setSalary(newEmp.getSalary());
		oldEmp.setDepartment(newEmp.getDepartment());

		return repo.save(oldEmp);
	}


	public void deleteEmployeeById(String id) {

		Optional<Employees> optional = repo.findById(id);

		if (!(optional.isPresent())) {
			throw new EmployeesNotFoundException("Could not found an employee with id: " + id);
		} else {
			repo.delete(optional.get());
		}
	}


	public void deleteAllEmployees() {
		repo.deleteAll();
	}


	//the employee who has the biggest salary in the given department
	public Employees maxSalary(String department) {

		List<Employees> list = repo.findByDepartment(department);
		Employees employee = Employees.builder().build();

		if (list.isEmpty()) {
			throw new DepartmentNotFoundException("Could not found department with the name: " + department);
		}

		double max = list.get(0).getSalary();
		for (Employees e : list) {
			if (max <= e.getSalary()) {
				max = e.getSalary();
				employee = e;
			}
		}

		return employee;
	}

	//the manager who has the most "direct" employees coordinated by him
	public Employees getDirect_Manager() {

		List<Employees> employeesList = repo.findAll();
		List<String> direct_ManagerList = new ArrayList<>();

		for (Employees e : employeesList) {
			direct_ManagerList.add(e.getDirect_manager());
		}

		Employees manager = Employees.builder().build();

		//find the most repeated manager in direct_manager column
		Map<String, Integer> map = new HashMap<>();

		for (String st : direct_ManagerList) {
			String key = st.toLowerCase();
			if (map.get(key) != null) {
				Integer value = map.get(key) + 1;
				map.put(key, value);
			} else {
				map.put(key, 1);
			}
		}

		//find the key which has the maximum value from the map
		String maxEntry = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();

		for (Employees e : employeesList) {
			if (maxEntry.equalsIgnoreCase(e.getDirect_manager())) {
				manager = e;
			}
		}

		return manager;
	}


	//BONUS POINTS
	//load a json file
	public void importJsonFile() {

		File fileJson = new File("/Users/cristi/Documents/Untitled_93.json");

		//Read each line of the json file. Each file is one observation document.
		List<Document> observationDocuments = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileJson.getPath()))) {
			String line;
			while ((line = br.readLine()) != null) {
				observationDocuments.add(Document.parse(line));
			}
		} catch (IOException ex) {
			ex.getStackTrace();
		}

		mongoTemplate.getCollection("employees").insertMany(observationDocuments);
	}


	//paging
	public Page<Employees> getPage(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		return repo.findAll(pageable);
	}


	//top n best paid employees in a given department
	public List<Employees> topNBest(String department, int n) {

		Criteria find = Criteria.where("department").is(department);
		Query query = new Query().addCriteria(find).with(Sort.by(Sort.Direction.DESC, "salary")).limit(n);

		List<Employees> employeesList = mongoTemplate.find(query, Employees.class);

		if (employeesList.isEmpty()) {
			throw new DepartmentNotFoundException("Could not found department with the name: " + department);
		}

		return employeesList;
	}


	//management tree: from the top CEO to the lowest employee
	public List<Employees> managementTree() {

		Query query = new Query().with(Sort.by(Sort.Direction.DESC, "salary"));
		return mongoTemplate.find(query, Employees.class);
	}
}