package com.practice.springdb;

import com.practice.springdb.entities.Employee;
import com.practice.springdb.entities.FullTimeEmployee;
import com.practice.springdb.entities.PartTimeEmployee;
import com.practice.springdb.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class JpaInheritanceApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaInheritanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee jack = new FullTimeEmployee("Jack", BigDecimal.valueOf(10000));
		Employee jill = new PartTimeEmployee("Jill", BigDecimal.valueOf(50));
		employeeRepository.insert(jack);
		employeeRepository.insert(jill);

//		List<Employee> employees = employeeRepository.retrieveAllEmployees();
//		logger.info("All employees: {}", employees);

		// when we made Employee class as MappedSuperclass so we have to query separately for different tables
		// as now we don't Employee mapped or we can say employee is not entity anymore with this change
//		List<FullTimeEmployee> allFullTimeEmployees = employeeRepository.retrieveAllFullTimeEmployees();
//		logger.info("All full time employees: {}", allFullTimeEmployees);
//
//		List<PartTimeEmployee> allPartTimeEmployees = employeeRepository.retrieveAllPartTimeEmployees();
//		logger.info("All Part time employees: {}", allPartTimeEmployees);
	}
}
