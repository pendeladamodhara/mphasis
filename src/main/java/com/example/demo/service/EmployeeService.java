package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	public boolean saveEmployee(Employee emp) {
		if (empRepo.save(emp).getEmpid() > 0) {
			return true;
		}
		return false;
	}

	public List<Employee> getEmployees() {
		List<Employee> e1 = new ArrayList<>();
		empRepo.findAll().forEach(e -> e1.add(e));
		return e1;

	}

}
