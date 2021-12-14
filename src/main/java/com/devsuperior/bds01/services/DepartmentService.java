package com.devsuperior.bds01.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.entities.Department;
import com.devsuperior.bds01.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	
	//TEM QUE FAZER UM MÉTODOS PARA RETORNAR UMA LISTA COM TODOS OS DEPARTAMENTOS:
	@Autowired
	private DepartmentRepository repository;
	
	public List<DepartmentDTO> findAll() {
		
		List<Department> list = repository.findAll(Sort.by("name"));
		
		//cada x será transformado em um new DepartmentDTO(x)
		return list.stream().map(x -> new DepartmentDTO(x)).collect(Collectors.toList());
	}
}
