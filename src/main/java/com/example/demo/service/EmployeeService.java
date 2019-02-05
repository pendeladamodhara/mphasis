package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public void deleteEmployee(String empId) {
		empRepo.deleteById(Integer.parseInt(empId));
	}

	public Optional<Employee> updateEmployee(Employee emp) {
		if (this.findById(emp.getEmpid()).isPresent()) {
			Employee e = this.findById(emp.getEmpid()).get();
			e.setEname(emp.getEname());
			e.setSal(emp.getSal());
			e.setDeptno(emp.getDeptno());
			return Optional.of(empRepo.save(e));
		}
		return Optional.empty();
	}

	public Optional<Employee> findById(Integer empId) {
		Optional<Employee> emp = empRepo.findById(empId);
		if (emp.isPresent()) {
			return emp;
		}
		return Optional.empty();
	}

}
