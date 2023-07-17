package employees.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import employees.exception.EmployeesNotFoundException;
import employees.modelMongo.Employees;
import employees.service.EmployeesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeesControllerTest {

  @Mock
  private EmployeesService service;

  @InjectMocks
  private EmployeesController controller;



  @Before
  public void setUp() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
  }


  @Test
  public void findAllEmployeesTest() throws Exception {

    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

    Employees eMock1 = new Employees();
    eMock1.set_id("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 1");
    eMock1.setSalary(444.44);
    eMock1.setDepartment("testeeee 1");

    Employees eMock2 = new Employees();
    eMock2.set_id("2");
    eMock2.setFirstName("testeeee 2");
    eMock2.setLastName("testeeee 2");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(444.45);
    eMock2.setDepartment("testeeee 2");

    Employees eMock3 = new Employees();
    eMock3.set_id("3");
    eMock3.setFirstName("testeeee 3");
    eMock3.setLastName("testeeee 3");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 3");
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
    assertEquals(listMockEmployees.get(0).getFirstName(),
        employeesControllerList.get(0).getFirstName());
    assertEquals(listMockEmployees.get(0).getDirectManager(),
        employeesControllerList.get(0).getDirectManager());

    assertEquals(listMockEmployees.get(1).getDob(), employeesControllerList.get(1).getDob());
    assertEquals(listMockEmployees.get(1).getSalary(), employeesControllerList.get(1).getSalary(), .1);
    assertEquals(listMockEmployees.get(1).getDepartment(),
        employeesControllerList.get(1).getDepartment());

    assertEquals(listMockEmployees.get(2).get_id(), employeesControllerList.get(2).get_id());
    assertEquals(listMockEmployees.get(2).getFirstName(),
        employeesControllerList.get(2).getFirstName());
    assertEquals(listMockEmployees.get(2).getDirectManager(),
        employeesControllerList.get(2).getDirectManager());

  }

  @Test
  public void findEmployeeByIdTest() throws Exception {

    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
    Employees eMock = new Employees();

    eMock.set_id("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDob(date);
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    //inject mock object in the controller
    when(service.getEmployeeById(((anyString())))).thenReturn((eMock));
    Employees employeesController = controller.findEmployeeById("11");

    assertNotNull(employeesController);
    assertEquals("11", eMock.get_id());
    assertEquals(eMock.getFirstName(), employeesController.getFirstName());
    assertEquals(eMock.getDirectManager(), employeesController.getDirectManager());

  }


  @Test
  public void saveEmployeeTest() throws ParseException {

    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
    Employees eMock = new Employees();

    eMock.set_id("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDob(date);
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    //inject mock object in the controller
    when(service.createEmployee((eMock))).thenReturn((eMock));
    Employees employeesController = controller.saveEmployee(eMock);

    assertNotNull(employeesController);
    assertEquals(eMock.get_id(), employeesController.get_id());
    assertEquals(eMock.getFirstName(), employeesController.getFirstName());
    assertEquals(eMock.getDirectManager(), employeesController.getDirectManager());

  }


  @Test
  public void saveEmployeeSTest() throws ParseException {

    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

    Employees eMock1 = new Employees();
    eMock1.set_id("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 1");
    eMock1.setSalary(444.44);
    eMock1.setDepartment("testeeee 1");

    Employees eMock2 = new Employees();
    eMock2.set_id("2");
    eMock2.setFirstName("testeeee 2");
    eMock2.setLastName("testeeee 2");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(444.45);
    eMock2.setDepartment("testeeee 2");

    Employees eMock3 = new Employees();
    eMock3.set_id("3");
    eMock3.setFirstName("testeeee 3");
    eMock3.setLastName("testeeee 3");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 3");
    eMock3.setSalary(444.46);
    eMock3.setDepartment("testeeee 3");

    List<Employees> listMockEmployees = new ArrayList<>();
    listMockEmployees.add(eMock1);
    listMockEmployees.add(eMock2);
    listMockEmployees.add(eMock3);

    when(service.createEmployee_s(listMockEmployees)).thenReturn((listMockEmployees));
    List<Employees> employeesControllerList = controller.saveEmployeeS(listMockEmployees);

    assertNotNull(employeesControllerList);
    assertEquals(listMockEmployees.get(0).get_id(), employeesControllerList.get(0).get_id());
    assertEquals(listMockEmployees.get(0).getFirstName(),
        employeesControllerList.get(0).getFirstName());
    assertEquals(listMockEmployees.get(0).getDirectManager(),
        employeesControllerList.get(0).getDirectManager());

    assertEquals(listMockEmployees.get(1).getDob(), employeesControllerList.get(1).getDob());
    assertEquals(listMockEmployees.get(1).getSalary(), employeesControllerList.get(1).getSalary(), .1);
    assertEquals(listMockEmployees.get(1).getDepartment(),
        employeesControllerList.get(1).getDepartment());

    assertEquals(listMockEmployees.get(2).get_id(), employeesControllerList.get(2).get_id());
    assertEquals(listMockEmployees.get(2).getFirstName(),
        employeesControllerList.get(2).getFirstName());
    assertEquals(listMockEmployees.get(2).getDirectManager(),
        employeesControllerList.get(2).getDirectManager());
  }


  @Test
  public void updateEmployeeTest() {

    Employees eMock = new Employees();

    eMock.set_id("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    Employees updateEmployee = new Employees();

    eMock.set_id("22");
    eMock.setFirstName("cristi2 teste");
    eMock.setLastName("cojocaru2 teste");
    eMock.setDirectManager("TESTE2");
    eMock.setSalary(555.55);
    eMock.setDepartment("IT-IT");

    //inject mock object in the controller
    when(service.updateEmployeeById(eMock.get_id(), updateEmployee)).thenReturn((updateEmployee));
    Employees employeesController = controller.updateEmployee(updateEmployee, eMock.get_id());

    assertNotNull(employeesController);
    assertEquals(updateEmployee.get_id(), employeesController.get_id());
    assertEquals(updateEmployee.getFirstName(), employeesController.getFirstName());
    assertEquals(updateEmployee.getDirectManager(), employeesController.getDirectManager());
  }


  @Test
  public void deleteByIdTest() {

    Employees eMock = new Employees();

    eMock.set_id("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    service.deleteEmployeeById(eMock.get_id());
    controller.deleteById(eMock.get_id());

  }

  @Test
  public void deleteAllTest() {

    Employees eMock = new Employees();

    eMock.set_id("11");
    eMock.setFirstName("cristi teste");
    eMock.setLastName("cojocaru teste");
    eMock.setDirectManager("TESTE");
    eMock.setSalary(444.44);
    eMock.setDepartment("IT");

    service.deleteAllEmployees();
    controller.deleteAll();

  }


  @Test(expected = EmployeesNotFoundException.class)
  public void itShouldThrowEmployeesNotFoundException() {

    when(service.getEmployeeById(anyString())).thenThrow(EmployeesNotFoundException.class);
    controller.findEmployeeById(anyString());

  }


  @Test
  public void maxSalaryTest() throws ParseException {


    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

    Employees eMock0 = new Employees();
    eMock0.set_id("1");
    eMock0.setFirstName("testeeee 1");
    eMock0.setLastName("testeeee 1");
    eMock0.setDob(date);
    eMock0.setDirectManager("TESTE 1");
    eMock0.setSalary(3.3);
    eMock0.setDepartment("dep 1");

    Employees eMock1 = new Employees();
    eMock1.set_id("2");
    eMock1.setFirstName("testeeee 2");
    eMock1.setLastName("testeeee 2");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(2.2);
    eMock1.setDepartment("dep 1");

    Employees eMock2 = new Employees();
    eMock2.set_id("3");
    eMock2.setFirstName("testeeee 3");
    eMock2.setLastName("testeeee 3");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 3");
    eMock2.setSalary(1.1);
    eMock2.setDepartment("dep 1");


    Employees eMock3 = new Employees();
    eMock3.set_id("1");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 1");
    eMock3.setSalary(6.6);
    eMock3.setDepartment("dep 2");

    Employees eMock4 = new Employees();
    eMock4.set_id("2");
    eMock4.setFirstName("testeeee 2");
    eMock4.setLastName("testeeee 2");
    eMock4.setDob(date);
    eMock4.setDirectManager("TESTE 2");
    eMock4.setSalary(5.5);
    eMock4.setDepartment("dep 2");

    Employees eMock5 = new Employees();
    eMock5.set_id("3");
    eMock5.setFirstName("testeeee 3");
    eMock5.setLastName("testeeee 3");
    eMock5.setDob(date);
    eMock5.setDirectManager("TESTE 3");
    eMock5.setSalary(4.4);
    eMock5.setDepartment("dep 2");

    List<Employees> listMockEmployeesDep = new ArrayList<>();
    listMockEmployeesDep.add(eMock0);
    listMockEmployeesDep.add(eMock1);
    listMockEmployeesDep.add(eMock2);
    listMockEmployeesDep.add(eMock3);
    listMockEmployeesDep.add(eMock4);
    listMockEmployeesDep.add(eMock5);

    when(service.maxSalary(eMock3.getDepartment())).thenReturn((eMock3));
    Employees employeesController =
        controller.findEmployeeByMaxSalaryByDepartment(eMock3.getDepartment());

    assertEquals(eMock3.getSalary(), employeesController.getSalary(), .1);
  }


  @Test
  public void getDirectManagerTest() throws ParseException {


    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

    Employees eMock0 = new Employees();
    eMock0.set_id("1");
    eMock0.setFirstName("testeeee 1");
    eMock0.setLastName("testeeee 1");
    eMock0.setDob(date);
    eMock0.setDirectManager("TESTE 2");
    eMock0.setSalary(3.3);
    eMock0.setDepartment("dep 1");

    Employees eMock1 = new Employees();
    eMock1.set_id("2");
    eMock1.setFirstName("testeeee 2");
    eMock1.setLastName("testeeee 2");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(2.2);
    eMock1.setDepartment("dep 1");

    Employees eMock2 = new Employees();
    eMock2.set_id("3");
    eMock2.setFirstName("testeeee 3");
    eMock2.setLastName("testeeee 3");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(1.1);
    eMock2.setDepartment("dep 1");


    Employees eMock3 = new Employees();
    eMock3.set_id("1");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 4");
    eMock3.setSalary(6.6);
    eMock3.setDepartment("dep 2");

    Employees eMock4 = new Employees();
    eMock4.set_id("2");
    eMock4.setFirstName("testeeee 2");
    eMock4.setLastName("testeeee 2");
    eMock4.setDob(date);
    eMock4.setDirectManager("TESTE 5");
    eMock4.setSalary(5.5);
    eMock4.setDepartment("dep 2");

    Employees eMock5 = new Employees();
    eMock5.set_id("3");
    eMock5.setFirstName("testeeee 3");
    eMock5.setLastName("testeeee 3");
    eMock5.setDob(date);
    eMock5.setDirectManager("TESTE 5");
    eMock5.setSalary(4.4);
    eMock5.setDepartment("dep 2");

    List<Employees> listMockEmployees = new ArrayList<>();
    listMockEmployees.add(eMock0);
    listMockEmployees.add(eMock1);
    listMockEmployees.add(eMock2);
    listMockEmployees.add(eMock3);
    listMockEmployees.add(eMock4);
    listMockEmployees.add(eMock5);

    when(service.getDirect_Manager()).thenReturn((eMock0));
    Employees employeesController = controller.getManager();

    assertEquals(eMock0.getDirectManager().toLowerCase(),
        employeesController.getDirectManager().toLowerCase());
  }


  //bonus points
  @Test
  public void getPageTest() throws ParseException {

    //first choice
    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

    Employees eMock1 = new Employees();
    eMock1.set_id("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(3.3);
    eMock1.setDepartment("dep 1");

    Employees eMock2 = new Employees();
    eMock2.set_id("2");
    eMock2.setFirstName("testeeee 1");
    eMock2.setLastName("testeeee 1");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(3.3);
    eMock2.setDepartment("dep 1");

    Employees eMock3 = new Employees();
    eMock3.set_id("3");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 2");
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
    assertEquals(employeesPageMock.getNumberOfElements(),
        employeesPageController.getNumberOfElements());

  }


  @Test
  public void topNBestTest() throws ParseException {

    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

    Employees eMock1 = new Employees();
    eMock1.set_id("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(3.3);
    eMock1.setDepartment("dep 1");

    Employees eMock2 = new Employees();
    eMock2.set_id("2");
    eMock2.setFirstName("testeeee 1");
    eMock2.setLastName("testeeee 1");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(4.4);
    eMock2.setDepartment("dep 1");

    Employees eMock3 = new Employees();
    eMock3.set_id("3");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 2");
    eMock3.setSalary(5.5);
    eMock3.setDepartment("dep 1");

    List<Employees> employeesListMock = new ArrayList<>();
    employeesListMock.add(eMock1);
    employeesListMock.add(eMock2);
    employeesListMock.add(eMock3);

    when(service.topNBest(eMock1.getDepartment(), 2)).thenReturn((employeesListMock));
    List<Employees> employeesControllerList = controller.topN(eMock1.getDepartment(), 2);

    assertEquals(eMock2.getSalary(), employeesControllerList.get(1).getSalary(), .1);
    assertEquals(eMock3.getSalary(), employeesControllerList.get(2).getSalary(), .1);

  }


  @Test
  public void managementTreeTest() throws ParseException {

    String sDate = "1984-11-25";
    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);

    Employees eMock1 = new Employees();
    eMock1.set_id("1");
    eMock1.setFirstName("testeeee 1");
    eMock1.setLastName("testeeee 1");
    eMock1.setDob(date);
    eMock1.setDirectManager("TESTE 2");
    eMock1.setSalary(3.3);
    eMock1.setDepartment("dep 1");

    Employees eMock2 = new Employees();
    eMock2.set_id("2");
    eMock2.setFirstName("testeeee 1");
    eMock2.setLastName("testeeee 1");
    eMock2.setDob(date);
    eMock2.setDirectManager("TESTE 2");
    eMock2.setSalary(6.6);
    eMock2.setDepartment("dep 1");

    Employees eMock3 = new Employees();
    eMock3.set_id("3");
    eMock3.setFirstName("testeeee 1");
    eMock3.setLastName("testeeee 1");
    eMock3.setDob(date);
    eMock3.setDirectManager("TESTE 2");
    eMock3.setSalary(5.5);
    eMock3.setDepartment("dep 1");

    List<Employees> employeesListMock = new ArrayList<>();
    employeesListMock.add(eMock1);
    employeesListMock.add(eMock2);
    employeesListMock.add(eMock3);

    when(service.managementTree()).thenReturn((employeesListMock));
    List<Employees> employeesControllerList = controller.tree();

    assertEquals(eMock1.getSalary(), employeesControllerList.get(0).getSalary(), .1);
    assertEquals(eMock2.getSalary(), employeesControllerList.get(1).getSalary(), .1);
    assertEquals(eMock3.getSalary(), employeesControllerList.get(2).getSalary(), .1);

  }
}