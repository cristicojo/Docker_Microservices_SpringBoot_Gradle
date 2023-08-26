package employee.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import employee.entity.Employee;
import employee.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

  @Mock
  private EmployeeService service;

  @InjectMocks
  private EmployeeController controller;



  @Before
  public void setUp() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
  }


  @Test
  public void findAllEmployeesTest() {

    LocalDate date = LocalDate.of(1984, 11, 25);

    Employee eMock1 = new Employee();
    eMock1.setId("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 1");
    eMock1.setSalary(444.44);
    eMock1.setDepartment("testeeee 1");

    Employee eMock2 = new Employee();
    eMock2.setId("2");
    eMock2.setFirstName("testeeee 2");
    eMock2.setLastName("testeeee 2");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(444.45);
    eMock2.setDepartment("testeeee 2");

    Employee eMock3 = new Employee();
    eMock3.setId("3");
    eMock3.setFirstName("testeeee 3");
    eMock3.setLastName("testeeee 3");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 3");
    eMock3.setSalary(444.46);
    eMock3.setDepartment("testeeee 3");

    List<Employee> listMockEmployees = new ArrayList<>();
    listMockEmployees.add(eMock1);
    listMockEmployees.add(eMock2);
    listMockEmployees.add(eMock3);

    when(service.getAll()).thenReturn((listMockEmployees));
    List<Employee> employeeControllerList = controller.findAll();

    assertNotNull(employeeControllerList);
    assertEquals(listMockEmployees.get(0).getId(), employeeControllerList.get(0).getId());
    assertEquals(listMockEmployees.get(0).getFirstName(),
        employeeControllerList.get(0).getFirstName());
    assertEquals(listMockEmployees.get(0).getDirectManager(),
        employeeControllerList.get(0).getDirectManager());

    assertEquals(listMockEmployees.get(1).getDob(), employeeControllerList.get(1).getDob());
    assertEquals(listMockEmployees.get(1).getSalary(), employeeControllerList.get(1).getSalary(), .1);
    assertEquals(listMockEmployees.get(1).getDepartment(),
        employeeControllerList.get(1).getDepartment());

    assertEquals(listMockEmployees.get(2).getId(), employeeControllerList.get(2).getId());
    assertEquals(listMockEmployees.get(2).getFirstName(),
        employeeControllerList.get(2).getFirstName());
    assertEquals(listMockEmployees.get(2).getDirectManager(),
        employeeControllerList.get(2).getDirectManager());

  }

  @Test
  public void findEmployeeByIdTest() {

    LocalDate date = LocalDate.of(1984, 11, 25);
    Employee eMock = new Employee();

    eMock.setId("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDob(date);
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    //inject mock object in the controller
    when(service.getById(((anyString())))).thenReturn((eMock));
    Employee employeeController = controller.findById("11");

    assertNotNull(employeeController);
    assertEquals("11", eMock.getId());
    assertEquals(eMock.getFirstName(), employeeController.getFirstName());
    assertEquals(eMock.getDirectManager(), employeeController.getDirectManager());

  }


  @Test
  public void saveEmployeeTest() {

    LocalDate date = LocalDate.of(1984, 11, 25);
    Employee eMock = new Employee();

    eMock.setId("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDob(date);
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    //inject mock object in the controller
    when(service.create((eMock))).thenReturn((eMock));
    Employee employeeController = controller.save(eMock);

    assertNotNull(employeeController);
    assertEquals(eMock.getId(), employeeController.getId());
    assertEquals(eMock.getFirstName(), employeeController.getFirstName());
    assertEquals(eMock.getDirectManager(), employeeController.getDirectManager());

  }


  @Test
  public void saveEmployeeSTest() {

    LocalDate date = LocalDate.of(1984, 11, 25);

    Employee eMock1 = new Employee();
    eMock1.setId("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 1");
    eMock1.setSalary(444.44);
    eMock1.setDepartment("testeeee 1");

    Employee eMock2 = new Employee();
    eMock2.setId("2");
    eMock2.setFirstName("testeeee 2");
    eMock2.setLastName("testeeee 2");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(444.45);
    eMock2.setDepartment("testeeee 2");

    Employee eMock3 = new Employee();
    eMock3.setId("3");
    eMock3.setFirstName("testeeee 3");
    eMock3.setLastName("testeeee 3");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 3");
    eMock3.setSalary(444.46);
    eMock3.setDepartment("testeeee 3");

    List<Employee> listMockEmployees = new ArrayList<>();
    listMockEmployees.add(eMock1);
    listMockEmployees.add(eMock2);
    listMockEmployees.add(eMock3);

    when(service.createAll(listMockEmployees)).thenReturn((listMockEmployees));
    List<Employee> employeeControllerList = controller.saveAll(listMockEmployees);

    assertNotNull(employeeControllerList);
    assertEquals(listMockEmployees.get(0).getId(), employeeControllerList.get(0).getId());
    assertEquals(listMockEmployees.get(0).getFirstName(),
        employeeControllerList.get(0).getFirstName());
    assertEquals(listMockEmployees.get(0).getDirectManager(),
        employeeControllerList.get(0).getDirectManager());

    assertEquals(listMockEmployees.get(1).getDob(), employeeControllerList.get(1).getDob());
    assertEquals(listMockEmployees.get(1).getSalary(), employeeControllerList.get(1).getSalary(), .1);
    assertEquals(listMockEmployees.get(1).getDepartment(),
        employeeControllerList.get(1).getDepartment());

    assertEquals(listMockEmployees.get(2).getId(), employeeControllerList.get(2).getId());
    assertEquals(listMockEmployees.get(2).getFirstName(),
        employeeControllerList.get(2).getFirstName());
    assertEquals(listMockEmployees.get(2).getDirectManager(),
        employeeControllerList.get(2).getDirectManager());
  }


  @Test
  public void updateEmployeeTest() {

    Employee eMock = new Employee();

    eMock.setId("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    Employee updateEmployee = new Employee();

    eMock.setId("22");
    eMock.setFirstName("cristi2 teste");
    eMock.setLastName("cojocaru2 teste");
    eMock.setDirectManager("TESTE2");
    eMock.setSalary(555.55);
    eMock.setDepartment("IT-IT");

    //inject mock object in the controller
    when(service.updateById(eMock.getId(), updateEmployee)).thenReturn((updateEmployee));
    Employee employeeController = controller.update(updateEmployee, eMock.getId());

    assertNotNull(employeeController);
    assertEquals(updateEmployee.getId(), employeeController.getId());
    assertEquals(updateEmployee.getFirstName(), employeeController.getFirstName());
    assertEquals(updateEmployee.getDirectManager(), employeeController.getDirectManager());
  }


  @Test
  public void deleteByIdTest() {

    Employee eMock = new Employee();

    eMock.setId("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    service.remove(eMock.getId());
    controller.deleteById(eMock.getId());

  }

  @Test
  public void deleteAllTest() {

    Employee eMock = new Employee();

    eMock.setId("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    service.removeAll();
    controller.deleteAll();

  }


  @Test(expected = ResourceNotFoundException.class)
  public void itShouldThrowEmployeesNotFoundException() {

    when(service.getById(anyString())).thenThrow(ResourceNotFoundException.class);
    controller.findById(anyString());

  }


  @Test
  public void maxSalaryTest() {


    LocalDate date = LocalDate.of(1984, 11, 25);

    Employee eMock0 = new Employee();
    eMock0.setId("1");
    eMock0.setFirstName("testeeee 1");
    eMock0.setLastName("testeeee 1");
    eMock0.setDob(date);
    eMock0.setDirectManager("TESTE 1");
    eMock0.setSalary(3.3);
    eMock0.setDepartment("dep 1");

    Employee eMock1 = new Employee();
    eMock1.setId("2");
    eMock1.setFirstName("testeeee 2");
    eMock1.setLastName("testeeee 2");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(2.2);
    eMock1.setDepartment("dep 1");

    Employee eMock2 = new Employee();
    eMock2.setId("3");
    eMock2.setFirstName("testeeee 3");
    eMock2.setLastName("testeeee 3");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 3");
    eMock2.setSalary(1.1);
    eMock2.setDepartment("dep 1");


    Employee eMock3 = new Employee();
    eMock3.setId("1");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 1");
    eMock3.setSalary(6.6);
    eMock3.setDepartment("dep 2");

    Employee eMock4 = new Employee();
    eMock4.setId("2");
    eMock4.setFirstName("testeeee 2");
    eMock4.setLastName("testeeee 2");
    eMock4.setDob(date);
    eMock4.setDirectManager("TESTE 2");
    eMock4.setSalary(5.5);
    eMock4.setDepartment("dep 2");

    Employee eMock5 = new Employee();
    eMock5.setId("3");
    eMock5.setFirstName("testeeee 3");
    eMock5.setLastName("testeeee 3");
    eMock5.setDob(date);
    eMock5.setDirectManager("TESTE 3");
    eMock5.setSalary(4.4);
    eMock5.setDepartment("dep 2");

    List<Employee> listMockEmployeeDep = new ArrayList<>();
    listMockEmployeeDep.add(eMock0);
    listMockEmployeeDep.add(eMock1);
    listMockEmployeeDep.add(eMock2);
    listMockEmployeeDep.add(eMock3);
    listMockEmployeeDep.add(eMock4);
    listMockEmployeeDep.add(eMock5);

    when(service.maxSalary(eMock3.getDepartment())).thenReturn((eMock3));
    Employee employeeController =
        controller.findByByDepartmentMaxSalary(eMock3.getDepartment());

    assertEquals(eMock3.getSalary(), employeeController.getSalary(), .1);
  }


  @Test
  public void getDirectManagerTest() {


    LocalDate date = LocalDate.of(1984, 11, 25);

    Employee eMock0 = new Employee();
    eMock0.setId("1");
    eMock0.setFirstName("testeeee 1");
    eMock0.setLastName("testeeee 1");
    eMock0.setDob(date);
    eMock0.setDirectManager("TESTE 2");
    eMock0.setSalary(3.3);
    eMock0.setDepartment("dep 1");

    Employee eMock1 = new Employee();
    eMock1.setId("2");
    eMock1.setFirstName("testeeee 2");
    eMock1.setLastName("testeeee 2");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(2.2);
    eMock1.setDepartment("dep 1");

    Employee eMock2 = new Employee();
    eMock2.setId("3");
    eMock2.setFirstName("testeeee 3");
    eMock2.setLastName("testeeee 3");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(1.1);
    eMock2.setDepartment("dep 1");


    Employee eMock3 = new Employee();
    eMock3.setId("1");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 4");
    eMock3.setSalary(6.6);
    eMock3.setDepartment("dep 2");

    Employee eMock4 = new Employee();
    eMock4.setId("2");
    eMock4.setFirstName("testeeee 2");
    eMock4.setLastName("testeeee 2");
    eMock4.setDob(date);
    eMock4.setDirectManager("TESTE 5");
    eMock4.setSalary(5.5);
    eMock4.setDepartment("dep 2");

    Employee eMock5 = new Employee();
    eMock5.setId("3");
    eMock5.setFirstName("testeeee 3");
    eMock5.setLastName("testeeee 3");
    eMock5.setDob(date);
    eMock5.setDirectManager("TESTE 5");
    eMock5.setSalary(4.4);
    eMock5.setDepartment("dep 2");

    List<Employee> listMockEmployees = new ArrayList<>();
    listMockEmployees.add(eMock0);
    listMockEmployees.add(eMock1);
    listMockEmployees.add(eMock2);
    listMockEmployees.add(eMock3);
    listMockEmployees.add(eMock4);
    listMockEmployees.add(eMock5);

    when(service.getDirectManager()).thenReturn((eMock0));
    Employee employeeController = controller.getManager();

    assertEquals(eMock0.getDirectManager().toLowerCase(),
        employeeController.getDirectManager().toLowerCase());
  }


  //bonus points
  @Test
  public void getPageTest() {

    //first choice
    LocalDate date = LocalDate.of(1984, 11, 25);

    Employee eMock1 = new Employee();
    eMock1.setId("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(3.3);
    eMock1.setDepartment("dep 1");

    Employee eMock2 = new Employee();
    eMock2.setId("2");
    eMock2.setFirstName("testeeee 1");
    eMock2.setLastName("testeeee 1");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(3.3);
    eMock2.setDepartment("dep 1");

    Employee eMock3 = new Employee();
    eMock3.setId("3");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 2");
    eMock3.setSalary(3.3);
    eMock3.setDepartment("dep 1");

    List<Employee> employeeListMock = new ArrayList<>();
    employeeListMock.add(eMock1);
    employeeListMock.add(eMock2);
    employeeListMock.add(eMock3);

    int pageNumber = 1;
    //3 elem per page
    int pageSize = 3;

    Page<Employee> employeesPageMock = new PageImpl<>(employeeListMock);
    when(service.getPage(pageNumber, pageSize)).thenReturn(employeesPageMock);
    Page<Employee> employeesPageController = controller.loadPage(pageNumber, pageSize);
    assertEquals(employeesPageMock.getNumberOfElements(),
        employeesPageController.getNumberOfElements());

  }


  @Test
  public void topNBestTest() {

    LocalDate date = LocalDate.of(1984, 11, 25);

    Employee eMock1 = new Employee();
    eMock1.setId("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(3.3);
    eMock1.setDepartment("dep 1");

    Employee eMock2 = new Employee();
    eMock2.setId("2");
    eMock2.setFirstName("testeeee 1");
    eMock2.setLastName("testeeee 1");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(4.4);
    eMock2.setDepartment("dep 1");

    Employee eMock3 = new Employee();
    eMock3.setId("3");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 2");
    eMock3.setSalary(5.5);
    eMock3.setDepartment("dep 1");

    List<Employee> employeeListMock = new ArrayList<>();
    employeeListMock.add(eMock1);
    employeeListMock.add(eMock2);
    employeeListMock.add(eMock3);

    when(service.topBestPaid(eMock1.getDepartment(), 2)).thenReturn((employeeListMock));
    List<Employee> employeeControllerList = controller.topN(eMock1.getDepartment(), 2);

    assertEquals(eMock2.getSalary(), employeeControllerList.get(1).getSalary(), .1);
    assertEquals(eMock3.getSalary(), employeeControllerList.get(2).getSalary(), .1);

  }


  @Test
  public void managementTreeTest() {

    LocalDate date = LocalDate.of(1984, 11, 25);

    Employee eMock1 = new Employee();
    eMock1.setId("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(3.3);
    eMock1.setDepartment("dep 1");

    Employee eMock2 = new Employee();
    eMock2.setId("2");
    eMock2.setFirstName("testeeee 1");
    eMock2.setLastName("testeeee 1");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(6.6);
    eMock2.setDepartment("dep 1");

    Employee eMock3 = new Employee();
    eMock3.setId("3");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 2");
    eMock3.setSalary(5.5);
    eMock3.setDepartment("dep 1");

    List<Employee> employeeListMock = new ArrayList<>();
    employeeListMock.add(eMock1);
    employeeListMock.add(eMock2);
    employeeListMock.add(eMock3);

    when(service.managementTree()).thenReturn((employeeListMock));
    List<Employee> employeeControllerList = controller.tree();

    assertEquals(eMock1.getSalary(), employeeControllerList.get(0).getSalary(), .1);
    assertEquals(eMock2.getSalary(), employeeControllerList.get(1).getSalary(), .1);
    assertEquals(eMock3.getSalary(), employeeControllerList.get(2).getSalary(), .1);

  }
}