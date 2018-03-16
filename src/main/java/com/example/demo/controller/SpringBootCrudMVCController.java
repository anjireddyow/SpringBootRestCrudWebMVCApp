package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.CustomConfigurationProperties;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.SpringBootRestCrudJDBCService;
import com.example.demo.service.SpringBootRestCrudService;

@Controller
public class SpringBootCrudMVCController {

	/**
	 * • To create a resource on the server, use POST. • To retrieve a resource, use
	 * GET. • To change the state of a resource or to update it, use PUT. • To
	 * remove or delete a resource, use DELETE.
	 * 
	 * JSON <==> Java The spring-boot-starter-web has built in jackson-databind,
	 * which helps to convert JSON into Java object and vice versa.
	 * 
	 * The Spring Boot uses JAXB (available in JDK) as a default library to convert
	 * XML and Java. However, Java classes need to be annotated
	 * by @XmlRootElement,... Therefore, my advice is that you should use the
	 * jackson-dataformat-xml as a library to convert XML and Java. To use the
	 * jackson-dataformat-xml, you need to declare it in the pom.xml file:
	 * 
	 * <dependency> <groupId>com.fasterxml.jackson.dataformat</groupId>
	 * <artifactId>jackson-dataformat-xml</artifactId> </dependency>
	 */

	@Autowired
	private SpringBootRestCrudJDBCService springBootRestCrudJDBCService;

	@Autowired
	private SpringBootRestCrudService springBootRestCrudService;

	/**
	 * This property value will be retrieved from application.properties file
	 * welcome.message property
	 */
	@Value("${welcome.message}")
	private String welcomeMessage;

	@Autowired
	private CustomConfigurationProperties customConfigurationProperties;

	/**
	 * Sample Welcome page
	 * 
	 * You can also use @GetMapping instead of @RequestMapping for request methods
	 * of GET
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String welcome() {
		return welcomeMessage + customConfigurationProperties.getMessage() + customConfigurationProperties.getAge();
	}

	/**
	 * 
	 * @return default spring boot login page
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("message", "Welcome to Spring Boot Rest CRUD MVC App");
		return "welcome";
	}

	/**
	 * 
	 * @return home page
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest httpServletRequest) {
		String appContextPath = httpServletRequest.getContextPath();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		// Get authenticated user name from SecurityContext
		SecurityContext context = SecurityContextHolder.getContext();

		modelAndView.addObject("message", "You are logged in as " + context.getAuthentication().getName());
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	/**
	 * To retrieve
	 * 
	 * // URL: // http://localhost:8080/SomeContextPath/employees //
	 * http://localhost:8080/SomeContextPath/employees.xml //
	 * http://localhost:8080/SomeContextPath/employees.json // produces = {
	 * MediaType.APPLICATION_JSON_VALUE, // MediaType.APPLICATION_XML_VALUE } --
	 * This tags will be helpful // to return the content both in either JSON or XML
	 * format
	 * 
	 * @return
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public List<Employee> getEmployees() {
		// return springBootRestCrudService.getEmployeeList();
		return springBootRestCrudService.getEmployees();
	}

	// // This is a GET request to retreive the resource (employee)
	// // {empNo} value will be dynamically passed from the url
	// // @PathVariable to be added for "empNo" parameter, to indicate empNo will be
	// // dynamically passed as a parameter
	// // Use Postman with GET request and Headers as
	// @RequestMapping(value = "/getEmployee/{empNo}", method = RequestMethod.GET,
	// produces = {
	// MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	// @ResponseBody
	// public Employee getEmployee(@PathVariable("empNo") String empNo) {
	// if ("04".equals(empNo)) {
	// throw new RuntimeException("Employee Number should not be 04");
	// }
	//
	// if ("05".equals(empNo)) {
	// throw new EmployeeNotFoundException("Employee Number should not be 05");
	// }
	// return employeeDAO.getEmployee(empNo);
	// }

	/**
	 * // This is a POST method for creating a resource (create a employee) //
	 * Employee information will be passed as a JSON or XML and "@RequestBody" will
	 * // be mapped with the passed employee object // Below sample data to add
	 * Employee // { // "empNo": "04", // "empName": "John04", // "empPosition":
	 * "Analyst04" // } // POST Request method for insert
	 * 
	 * @param addEmployee
	 * @return
	 */
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public String addEmployee(@RequestBody Employee addEmployee) {
		String addEmployeeStatus = "";
		try {
			addEmployeeStatus = springBootRestCrudJDBCService.addEmployee(addEmployee);
		} catch (DataAccessException dae) {
			throw new EmployeeNotFoundException("Unable to add Employee" + dae.getMessage());
		}
		return addEmployeeStatus;
	}

	/**
	 * // Below sample data to update Employee using Postman with PUT request and //
	 * update headers and body with the below JSON // { // "empNo": "04", //
	 * "empName": "John04", // "empPosition": "Analyst04" // } // PUT Request method
	 * for update
	 * 
	 * @param updateEmployee
	 */
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public void updateEmployee(@RequestBody Employee updateEmployee) {
		springBootRestCrudJDBCService.updateEmployee(updateEmployee);
	}

	// DELETE Request method for delete
	@RequestMapping(value = "/deleteEmployee/{empNo}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public void deleteEmployee(@PathVariable("empNo") int empNo) {
		springBootRestCrudJDBCService.deleteEmployee(empNo);
	}

}
