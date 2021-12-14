package com.devsuperior.bds01.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.entities.Employee;
import com.devsuperior.bds01.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	//02-41 Implementando o desafio resolvido - após os 10:00 minutos
	@Autowired
	private EmployeeRepository repository;
	
	@Transactional(readOnly=true)
	public Page<EmployeeDTO> findAll(Pageable pageable) {
		
		Page<Employee> page = repository.findAll(pageable);
		
		//cada x será transformado em um new EmployeeDTO(x)
		return page.map(x -> new EmployeeDTO(x));
	}

	@Transactional //copiado de DScatalog/CategoryService:
	public EmployeeDTO insert(EmployeeDTO dto) {
		
		Employee entity=new Employee();
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setDepartment(new Department(dto.getDepartmentId(), null));
		entity=repository.save(entity);
		return new EmployeeDTO(entity);
	}
}