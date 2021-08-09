package employees.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import employees.exception.EmployeesNotFoundException;
import employees.modelMongo.Employees;
import employees.service.EmployeesService;
import io.restassured.response.Response;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeesControllerTest {

	@Mock
	private EmployeesService service;

	@InjectMocks
	private EmployeesController controller;


	private final String CONTEXT_PATH = "/rest_api";


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	@Order(1)
	public void findAllEmployeesTest() throws Exception {

		String sDate = "1984-11-25";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

		Employees eMock1 = new Employees();
		eMock1.set_id("1");
		eMock1.setFirst_name("testeeee 1");
		eMock1.setLast_name("testeeee 1");
		eMock1.setDob(date);
		eMock1.setDirect_manager("TESTE 1");
		eMock1.setSalary(444.44);
		eMock1.setDepartment("testeeee 1");

		Employees eMock2 = new Employees();
		eMock2.set_id("2");
		eMock2.setFirst_name("testeeee 2");
		eMock2.setLast_name("testeeee 2");
		eMock2.setDob(date);
		eMock2.setDirect_manager("TESTE 2");
		eMock2.setSalary(444.45);
		eMock2.setDepartment("testeeee 2");

		Employees eMock3 = new Employees();
		eMock3.set_id("3");
		eMock3.setFirst_name("testeeee 3");
		eMock3.setLast_name("testeeee 3");
		eMock3.setDob(date);
		eMock3.setDirect_manager("TESTE 3");
		eMock3.setSalary(444.46);
		eMock3.setDepartment("testeeee 3");

		List<Employees> listMockEmployees = new ArrayList<>();
		listMockEmployees.add(eMock1);
		listMockEmployees.add(eMock2);
		listMockEmployees.add(eMock3);

		when(service.getEmployees()).thenReturn((listMockEmployees));
		List<Employees> employeesControllerList = controller.findAllEmployees();

		assertNotNull(employeesControllerList);
		assertEquals(listMockEmployees.get(0).get_id(), employeesControllerList.get(0).get_id());
		assertEquals(listMockEmployees.get(0).getFirst_name(), employeesControllerList.get(0).getFirst_name());
		assertEquals(listMockEmployees.get(0).getDirect_manager(), employeesControllerList.get(0).getDirect_manager());

		assertEquals(listMockEmployees.get(1).getDob(), employeesControllerList.get(1).getDob());
		assertEquals(listMockEmployees.get(1).getSalary(), employeesControllerList.get(1).getSalary());
		assertEquals(listMockEmployees.get(1).getDepartment(), employeesControllerList.get(1).getDepartment());

		assertEquals(listMockEmployees.get(2).get_id(), employeesControllerList.get(2).get_id());
		assertEquals(listMockEmployees.get(2).getFirst_name(), employeesControllerList.get(2).getFirst_name());
		assertEquals(listMockEmployees.get(2).getDirect_manager(), employeesControllerList.get(2).getDirect_manager());

	}

	@Test
	@Order(2)
	public void findEmployeeByIdTest() throws Exception {

		String sDate = "1984-11-25";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		Employees eMock = new Employees();

		eMock.set_id("11");
		eMock.setFirst_name("cristi teste");
		eMock.setLast_name("cojocaru teste");
		eMock.setDob(date);
		eMock.setDirect_manager("TESTE");
		eMock.setSalary(444.44);
		eMock.setDepartment("IT");

		//inject mock object in the controller
		when(service.getEmployeeById(((anyString())))).thenReturn((eMock));
		Employees employeesController = controller.findEmployeeById("11");

		assertNotNull(employeesController);
		assertEquals("11", eMock.get_id());
		assertEquals(eMock.getFirst_name(), employeesController.getFirst_name());
		assertEquals(eMock.getDirect_manager(), employeesController.getDirect_manager());

	}


	@Test
	@Order(3)
	public void saveEmployeeTest() throws ParseException {

		String sDate = "1984-11-25";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
		Map<String, Object> employees = new HashMap<>();

		employees.put("first_name", "PROBE 1");
		employees.put("last_name", "PROBE 11");
		employees.put("dob", date);
		employees.put("direct_manager", "PROBE 111");
		employees.put("salary", 0.0);
		employees.put("department", "PROBE 1111");

		Response response = given().contentType("application/json").accept("application/json").body(employees).
				when().post(CONTEXT_PATH + "/employee").
				then().statusCode(200).contentType("application/json").
				extract().response();

		Double d = response.jsonPath().getDouble("salary");
		assertEquals(d, employees.get("salary"));

	}


	@Test
	@Order(4)
	public void saveEmployeeSTest() throws ParseException {

		String sDate = "2020-03-29";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

		Map<String, Object> employees1 = new HashMap<>();
		employees1.put("first_name", "PROBE 1");
		employees1.put("last_name", "PROBE 11");
		employees1.put("dob", date);
		employees1.put("direct_manager", "PROBE 111");
		employees1.put("salary", 0.0);
		employees1.put("department", "PROBE 1111");

		Map<String, Object> employees2 = new HashMap<>();
		employees2.put("first_name", "PROBE 1");
		employees2.put("last_name", "PROBE 11");
		employees2.put("dob", date);
		employees2.put("direct_manager", "PROBE 111");
		employees2.put("salary", 0.0);
		employees2.put("department", "PROBE 1111");

		Map<String, Object> employees3 = new HashMap<>();
		employees3.put("first_name", "PROBE 1");
		employees3.put("last_name", "PROBE 11");
		employees3.put("dob", date);
		employees3.put("direct_manager", "PROBE 111");
		employees3.put("salary", 0.0);
		employees3.put("department", "PROBE 1111");

		List<Map<String, Object>> employeesList = new ArrayList<>();
		employeesList.add(employees1);
		employeesList.add(employees2);
		employeesList.add(employees3);

		Response response = given().contentType("application/json").accept("application/json").body(employeesList).
				when().post(CONTEXT_PATH + "/employees").
				then().statusCode(200).contentType("application/json").
				extract().response();

		List<HashMap> listResponse = response.jsonPath().getList("", HashMap.class);
		assertEquals(listResponse.get(0).get("salary"), employeesList.get(0).get("salary"));
	}


	@Test
	@Order(5)
	public void updateEmployeeTest() throws JSONException {

		String sDate = "2018-04-10T22:00:00.000Z";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		LocalDate date = LocalDate.parse(sDate, formatter);
		String _id = "5e7ca85e12d4c247501f2724";
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("_id", _id);
		jsonObject.put("first_name", "herlo11 herlo11");
		jsonObject.put("last_name", "PROBE 11");
		jsonObject.put("dob", date);
		jsonObject.put("direct_manager", "PROBE 111");
		jsonObject.put("salary", 1.0);
		jsonObject.put("department", "PROBE");

		Response response = given().contentType("application/json").accept("application/json").
				body(jsonObject.toString()).when().put(CONTEXT_PATH + "/employee/" + _id).
				then().statusCode(200).contentType("application/json").extract().response();

		String s = response.jsonPath().getString("first_name");
		assertEquals(s, jsonObject.get("first_name"));
	}


	@Test
	@Order(6)
	public void deleteByIdTest() {

		Response response = given().delete(CONTEXT_PATH + "/employee/5e7ca85e12d4c247501f2724");
		int r = response.getStatusCode();
		System.out.println(r);

		assertEquals(r, 200);

	}

	@Disabled
	@Test
	public void deleteAllTest() {

		Response response = given().delete(CONTEXT_PATH + "/all");
		int r = response.getStatusCode();
		System.out.println(r);

		assertEquals(r, 200);

	}


	@Test
	@Order(7)
	public void itShouldThrowEmployeesNotFoundException() {

		String _id = "1";
		Response response = given().get(CONTEXT_PATH + "/employee/" + _id);
		String s = response.jsonPath().getString("_id");

		EmployeesNotFoundException exception = assertThrows(EmployeesNotFoundException.class, () -> {
			if (s == null) {
				throw new EmployeesNotFoundException("Could not found an employee with id: " + _id);
			}
		});

		assertEquals(exception.getMessage(), "Could not found an employee with id: " + _id);
	}


	@Test
	@Order(8)
	public void maxSalaryTest() throws ParseException {


		String sDate = "1984-11-25";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

		Employees eMock0 = new Employees();
		eMock0.set_id("1");
		eMock0.setFirst_name("testeeee 1");
		eMock0.setLast_name("testeeee 1");
		eMock0.setDob(date);
		eMock0.setDirect_manager("TESTE 1");
		eMock0.setSalary(3.3);
		eMock0.setDepartment("dep 1");

		Employees eMock1 = new Employees();
		eMock1.set_id("2");
		eMock1.setFirst_name("testeeee 2");
		eMock1.setLast_name("testeeee 2");
		eMock1.setDob(date);
		eMock1.setDirect_manager("TESTE 2");
		eMock1.setSalary(2.2);
		eMock1.setDepartment("dep 1");

		Employees eMock2 = new Employees();
		eMock2.set_id("3");
		eMock2.setFirst_name("testeeee 3");
		eMock2.setLast_name("testeeee 3");
		eMock2.setDob(date);
		eMock2.setDirect_manager("TESTE 3");
		eMock2.setSalary(1.1);
		eMock2.setDepartment("dep 1");


		Employees eMock3 = new Employees();
		eMock3.set_id("1");
		eMock3.setFirst_name("testeeee 1");
		eMock3.setLast_name("testeeee 1");
		eMock3.setDob(date);
		eMock3.setDirect_manager("TESTE 1");
		eMock3.setSalary(6.6);
		eMock3.setDepartment("dep 2");

		Employees eMock4 = new Employees();
		eMock4.set_id("2");
		eMock4.setFirst_name("testeeee 2");
		eMock4.setLast_name("testeeee 2");
		eMock4.setDob(date);
		eMock4.setDirect_manager("TESTE 2");
		eMock4.setSalary(5.5);
		eMock4.setDepartment("dep 2");

		Employees eMock5 = new Employees();
		eMock5.set_id("3");
		eMock5.setFirst_name("testeeee 3");
		eMock5.setLast_name("testeeee 3");
		eMock5.setDob(date);
		eMock5.setDirect_manager("TESTE 3");
		eMock5.setSalary(4.4);
		eMock5.setDepartment("dep 2");

		List<Employees> listMockEmployeesDep = new ArrayList<>();
		listMockEmployeesDep.add(eMock0);
		listMockEmployeesDep.add(eMock1);
		listMockEmployeesDep.add(eMock2);
		listMockEmployeesDep.add(eMock3);
		listMockEmployeesDep.add(eMock4);
		listMockEmployeesDep.add(eMock5);


		when(service.getEmployees()).thenReturn((listMockEmployeesDep));
		List<Employees> employeesControllerList = controller.findAllEmployees();
		double max = employeesControllerList.get(0).getSalary();
		Employees eMock = Employees.builder().build();
		String givenDep = "dep 2";

		for (Employees e : employeesControllerList) {
			if (e.getDepartment().equalsIgnoreCase(givenDep)) {
				if (max <= e.getSalary()) {
					max = e.getSalary();
					eMock = e;
				}
			}
		}

		assertEquals(eMock.getSalary(), employeesControllerList.get(3).getSalary());
	}


	@Test
	@Order(9)
	public void getDirect_ManagerTest() throws ParseException {


		String sDate = "1984-11-25";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

		Employees eMock0 = new Employees();
		eMock0.set_id("1");
		eMock0.setFirst_name("testeeee 1");
		eMock0.setLast_name("testeeee 1");
		eMock0.setDob(date);
		eMock0.setDirect_manager("TESTE 2");
		eMock0.setSalary(3.3);
		eMock0.setDepartment("dep 1");

		Employees eMock1 = new Employees();
		eMock1.set_id("2");
		eMock1.setFirst_name("testeeee 2");
		eMock1.setLast_name("testeeee 2");
		eMock1.setDob(date);
		eMock1.setDirect_manager("TESTE 2");
		eMock1.setSalary(2.2);
		eMock1.setDepartment("dep 1");

		Employees eMock2 = new Employees();
		eMock2.set_id("3");
		eMock2.setFirst_name("testeeee 3");
		eMock2.setLast_name("testeeee 3");
		eMock2.setDob(date);
		eMock2.setDirect_manager("TESTE 2");
		eMock2.setSalary(1.1);
		eMock2.setDepartment("dep 1");


		Employees eMock3 = new Employees();
		eMock3.set_id("1");
		eMock3.setFirst_name("testeeee 1");
		eMock3.setLast_name("testeeee 1");
		eMock3.setDob(date);
		eMock3.setDirect_manager("TESTE 4");
		eMock3.setSalary(6.6);
		eMock3.setDepartment("dep 2");

		Employees eMock4 = new Employees();
		eMock4.set_id("2");
		eMock4.setFirst_name("testeeee 2");
		eMock4.setLast_name("testeeee 2");
		eMock4.setDob(date);
		eMock4.setDirect_manager("TESTE 5");
		eMock4.setSalary(5.5);
		eMock4.setDepartment("dep 2");

		Employees eMock5 = new Employees();
		eMock5.set_id("3");
		eMock5.setFirst_name("testeeee 3");
		eMock5.setLast_name("testeeee 3");
		eMock5.setDob(date);
		eMock5.setDirect_manager("TESTE 5");
		eMock5.setSalary(4.4);
		eMock5.setDepartment("dep 2");

		List<Employees> listMockEmployees = new ArrayList<>();
		listMockEmployees.add(eMock0);
		listMockEmployees.add(eMock1);
		listMockEmployees.add(eMock2);
		listMockEmployees.add(eMock3);
		listMockEmployees.add(eMock4);
		listMockEmployees.add(eMock5);

		when(service.getEmployees()).thenReturn((listMockEmployees));
		List<Employees> employeesControllerList = controller.findAllEmployees();
		List<String> direct_ManagerList = new ArrayList<>();

		for (Employees e : employeesControllerList) {
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

		for (Employees e : employeesControllerList) {
			if (maxEntry.equalsIgnoreCase(e.getDirect_manager())) {
				manager = e;
			}
		}

		assertEquals(manager.getDirect_manager().toLowerCase(), employeesControllerList.get(0).getDirect_manager().toLowerCase());
	}


	//bonus points
	@Test
	@Order(10)
	public void getPageTest() throws ParseException {

		//first choice
		String sDate = "1984-11-25";
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

		Employees eMock1 = new Employees();
		eMock1.set_id("1");
		eMock1.setFirst_name("testeeee 1");
		eMock1.setLast_name("testeeee 1");
		eMock1.setDob(date);
		eMock1.setDirect_manager("TESTE 2");
		eMock1.setSalary(3.3);
		eMock1.setDepartment("dep 1");

		Employees eMock2 = new Employees();
		eMock2.set_id("2");
		eMock2.setFirst_name("testeeee 1");
		eMock2.setLast_name("testeeee 1");
		eMock2.setDob(date);
		eMock2.setDirect_manager("TESTE 2");
		eMock2.setSalary(3.3);
		eMock2.setDepartment("dep 1");

		Employees eMock3 = new Employees();
		eMock3.set_id("3");
		eMock3.setFirst_name("testeeee 1");
		eMock3.setLast_name("testeeee 1");
		eMock3.setDob(date);
		eMock3.setDirect_manager("TESTE 2");
		eMock3.setSalary(3.3);
		eMock3.setDepartment("dep 1");

		List<Employees> employeesListMock = new ArrayList<>();
		employeesListMock.add(eMock1);
		employeesListMock.add(eMock2);
		employeesListMock.add(eMock3);

		int pageNumber = 1;
		//3 elem per page
		int pageSize = 3;

		Page<Employees> employeesPageMock = new PageImpl<>(employeesListMock);
		when(service.getPage(pageNumber, pageSize)).thenReturn(employeesPageMock);
		Page<Employees> employeesPageController = controller.loadPage(pageNumber, pageSize);
		assertEquals(employeesPageMock.getNumberOfElements(), employeesPageController.getNumberOfElements());

		//second choice
//		Response response = RestAssured.get( "http://localhost:8080/rest_api/page=3&size=8");
//		assertThat(response.getStatusCode(), is(200));

	}


	@Test
	@Order(11)
	public void topNBestTest() {

		Response response = given().contentType("application/json").accept("application/json").
				when().get(CONTEXT_PATH + "/top/it/3").
				then().statusCode(200).contentType("application/json").extract().response();

		List<Double> doubleList = response.jsonPath().get("salary");
		assertThat(doubleList.toString(), equalTo("[10000.0, 999.0, 567.0]"));

	}


	@Test
	public void managementTreeTest() {


		Response response = given().contentType("application/json").accept("application/json").
				when().get(CONTEXT_PATH + "/tree").
				then().statusCode(200).contentType("application/json").extract().response();

		List<Double> doubleList = response.jsonPath().get("salary");
		assertThat(doubleList.toString(), equalTo("[123.56, 123.123, 111.22, 90.45, 77.88, 67.9, 56.8, 11.22, 11.22, 0.0, 0.0, 0.0, 0.0]"));

	}
}