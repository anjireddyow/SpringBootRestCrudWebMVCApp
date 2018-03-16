package com.example.demo.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.common.CommonConstants;
import com.example.demo.config.MessageConfigurationProperties;
import com.example.demo.dao.EmployeeJDBCTemplateDao;
import com.example.demo.model.Employee;

/**
 * * Spring provides JdbcTemplate class for database operations using JDBC.
 * JdbcTemplate class is auto-configured and we get its object using @Autowire
 * annotation in our class that is annotated with spring stereotypes such
 * as @Component. JdbcTemplate provides methods such as queryForObject(),
 * query(), update() etc to perform database operations. In
 * application.properties file we configure DataSource and connection pooling.
 * Spring boot chooses tomcat pooling by default. Transaction management is
 * performed by using spring @Transactional annotation either at class level or
 * method level. Spring JDBC provides RowMapper interface that is implemented to
 * map a database table row with java object. If table column name and java
 * entity fields name are same, then we can directly use Spring JDBC
 * BeanPropertyRowMapper to map a row with java object.
 * 
 * JdbcTemplate : Run SQL Queries JdbcTemplate provides methods to run DML and
 * DDL SQL queries. Find the example of some of them. a.
 * JdbcTemplate.queryForObject :
 * 
 * <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args)
 * 
 * This method fetches data for a given SQL query as an object using RowMapper.
 * SQL query can have bind parameters. Find the description of parameters. sql:
 * SQL containing bind parameter. rowMapper: Object of RowMapper implemented
 * class. RowMapper will map one object per row. args: Arguments that bind to
 * the query.
 * 
 * b. JdbcTemplate.query:
 * 
 * <T> List<T> query(String sql,RowMapper<T> rowMapper)
 * 
 * This method executes static query and maps rows to java objects using
 * RowMapper. Find the description of parameters. sql: SQL query to execute.
 * rowMapper: Object of RowMapper implemented class. RowMapper will map one
 * object per row.
 * 
 * c. JdbcTemplate.update:
 * 
 * int update(String sql, Object... args)
 * 
 * This method executes insert, update and delete statements. Find the
 * description of parameters. sql: SQL containing bind parameter. args:
 * Arguments that bind to the query.
 * 
 * @author
 *
 */
@Repository
public class EmployeeJDBCTemplateDaoImpl implements EmployeeJDBCTemplateDao {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeJDBCTemplateDaoImpl.class);

	private static final String GET_EMPLOYEES_SQL = "SELECT employee_number, employee_name, employee_position FROM employee";
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO EMPLOYEE (EMPLOYEE_NUMBER, EMPLOYEE_NAME, EMPLOYEE_POSITION) VALUES (?,?,?)";
	private static final String UPDATE_EMPLOYEE_SQL = "UPDATE EMPLOYEE SET EMPLOYEE_NAME = ?, EMPLOYEE_POSITION = ? WHERE EMPLOYEE_NUMBER = ?";
	private static final String DELETE_EMPLOYEE_SQL = "DELETE FROM EMPLOYEE WHERE EMPLOYEE_NUMBER = ?";

	/**
	 * Create a Database as : springboot
	 * 
	 * Table Name as : Employee Columns : employee_number as int with primary key
	 * employee_name as varchar(45) employee_position as varchar(45)
	 * 
	 */
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MessageConfigurationProperties messageConfigurationProperties;

	@Autowired
	public EmployeeJDBCTemplateDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @see EmployeeJDBCTemplateDao
	 */
	public List<Employee> getEmployees() {
		RowMapper<Employee> rowMapper = new EmployeeRowMapper();
		return this.jdbcTemplate.query(GET_EMPLOYEES_SQL, rowMapper);
		// Instead of Custom EmployeeRowMapper, we can use the BeanPropertyRowMapper
		// RowMapper<Employee> allEmployeesRowMapper = new
		// BeanPropertyRowMapper<Employee>(Employee.class);
	}

	/**
	 * @see EmployeeJDBCTemplateDao
	 */
	public String addEmployee(Employee addEmployee) throws DataAccessException {
		String addEmployeeStatus = messageConfigurationProperties.getAddemployeefailure();
		int insertStatus = this.jdbcTemplate.update(INSERT_EMPLOYEE_SQL, addEmployee.getEmpNo(),
				addEmployee.getEmpName(), addEmployee.getEmpPosition());
		if (insertStatus == CommonConstants.DATABASE_SUCCESS) {
			addEmployeeStatus = messageConfigurationProperties.getAddemployeesuccess();
		}
		logger.info(CommonConstants.APP_NAME_FOR_LOG + "Employee adding with the status :" + insertStatus);
		return addEmployeeStatus;
	}

	/**
	 * @see EmployeeJDBCTemplateDao
	 */
	public void updateEmployee(Employee updateEmployee) {
		int updateStatus = this.jdbcTemplate.update(UPDATE_EMPLOYEE_SQL, updateEmployee.getEmpName(),
				updateEmployee.getEmpPosition(), updateEmployee.getEmpNo());
		logger.info(CommonConstants.APP_NAME_FOR_LOG + "Employee Updated with the status :" + updateStatus);
	}

	/**
	 * @see EmployeeJDBCTemplateDao
	 */
	public void deleteEmployee(int employee_number) {
		int deleteStatus = this.jdbcTemplate.update(DELETE_EMPLOYEE_SQL, employee_number);
		logger.info(CommonConstants.APP_NAME_FOR_LOG + "Employee adding with the status :" + deleteStatus);
	}
}
