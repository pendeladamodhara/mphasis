package com.example.demo.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(value = "/getEmployees", produces = "application/json")
	public List<Employee> getEmployees() {

		List<Employee> listOfEmployess = employeeService.getEmployees();
		if (listOfEmployess.isEmpty()) {
			return new ArrayList<>();
		} else {
			return listOfEmployess;
		}
	}

	@DeleteMapping(value = "deleteEmployee/{empId}", produces = "application/json")
	public String deleteEmployee(@PathVariable("empId") String empid) {
		Optional<String> opt = Optional.ofNullable(empid);

		if (opt.isPresent()) {
			employeeService.deleteEmployee(empid);
			return "Employee deleted successfully";
		}
		return "No Employee available";
	}

	@PutMapping(produces = "application/json", value = "/updateEmployee", consumes = "application/json")
	public String updateEmployee(@RequestBody Employee emp) {

		if (employeeService.updateEmployee(emp).isPresent()) {
			return "Employee updated successfully";
		}

		return "No data has updated";

	}

}
