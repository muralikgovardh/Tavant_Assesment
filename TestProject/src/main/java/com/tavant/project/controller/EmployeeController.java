package com.tavant.project.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tavant.project.exception.CannotAddEmployeeException;
import com.tavant.project.exception.EmployeeNotFoundException;
import com.tavant.project.exception.NoDataFoundException;
import com.tavant.project.model.Employee;
import com.tavant.project.repository.EmployeeRepository;

// for spring we use @controller 
// for rest we use @RestContoller

@RestController
@RequestMapping("/api/employee")


public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping
	public String getEmployee() //(1)
	{
		return "Welcome to the Jungle";
		
	}
	
	@GetMapping("/all") // return all resources (6)
	public List<Employee> getAllEmployees() throws Exception
	{
			
		List list = this.employeeRepository.findAll();
        return Optional.ofNullable(list.isEmpty() ? null : list).orElseThrow(()->new NoDataFoundException("No data found"));
		
	}
	
	@GetMapping("/{id}") // read an account resource (3)
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) throws EmployeeNotFoundException
	{
				
		Optional<Employee> optional= employeeRepository.findById(id);
		
		if(optional.isPresent())
		{
			return ResponseEntity.ok(optional.get());
		}
		else
		{
			throw new EmployeeNotFoundException("Resource not found");
		}
		
		
	}
	
	@PostMapping // creating a new resource(2)
	public Employee addEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException
	{
		if(employee.getEmployeeId()==null)
		{
			throw new EmployeeNotFoundException("Cannot Add Employee");
		}
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/delete/{id}") // delete resource (5)
	public String deleteEmployee(@PathVariable("id") Integer id) throws EmployeeNotFoundException
	{
		Optional<Employee> optional= employeeRepository.findById(id);
		
		if(optional.isPresent())
		{
			employeeRepository.deleteById(id);
			return "Resource Deleted Successfully";
		}
		else
		{
			throw new EmployeeNotFoundException("Resource not found");
		}
	}
	@PutMapping("/{Id}")
    public ResponseEntity<Employee> updateAccount(@PathVariable("Id") Integer id , @Valid @RequestBody Employee resourceDetails) throws EmployeeNotFoundException {
       
        Employee acc = employeeRepository.findById(id).
                orElseThrow(()->new EmployeeNotFoundException("Record Not Found"));
               
        
        acc.setFirstName(resourceDetails.getFirstName());
        acc.setLastName(resourceDetails.getLastName());
        acc.setEmail(resourceDetails.getEmail());
       
        final Employee newDetails= employeeRepository.save(acc);
        return ResponseEntity.ok(newDetails);
    }
	
	
}
