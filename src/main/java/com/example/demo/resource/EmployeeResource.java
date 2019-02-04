package com.example.demo.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(consumes = "application/json", produces = "application/json", value = "/createEmployee")
	public String createEmployee(@RequestBody Employee emp) {
		boolean flag = employeeService.saveEmployee(emp);

		if (flag) {
			return "employee added to list";
		} else {
			return "failed  to add  into list";

		}
	}

	
	@GetMapping(value="/getEmployees",produces = "application/json")
	public List<Employee> getEmployees() {

		List<Employee> listOfEmployess = employeeService.getEmployees();
		if (listOfEmployess.isEmpty()) {
			return new ArrayList<>();
		} else {
			return listOfEmployess;
		}
	}

}
