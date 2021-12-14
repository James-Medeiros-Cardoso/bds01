package com.devsuperior.bds01.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds01.dto.DepartmentDTO;
import com.devsuperior.bds01.services.DepartmentService;

@RestController
@RequestMapping(value="/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> findAll() {
			
			/*@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,	
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy*/
			
		//PageRequest pageRequest=PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<DepartmentDTO> list=service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
}