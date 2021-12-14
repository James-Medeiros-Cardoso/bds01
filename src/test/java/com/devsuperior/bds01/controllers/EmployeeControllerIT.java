package com.devsuperior.bds01.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds01.dto.EmployeeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EmployeeControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test //retorna os recursos (dados) paginados e ordenados por nome
	public void findAllShouldReturnPagedResourcesSortedByName() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/employees")
					.contentType(MediaType.APPLICATION_JSON)); //tem que retornar ordenado por nome sem estar na requisição

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].name").value("Alex"));
		result.andExpect(jsonPath("$.content[1].name").value("Ana"));
		result.andExpect(jsonPath("$.content[2].name").value("Andressa"));
	}
	
	@Test //deve inserir um recurso
	public void insertShouldInsertResource() throws Exception {

		//instanciar o empregado e o id do departamento
		EmployeeDTO dto = new EmployeeDTO(null, "Joaquim", "joaquim@gmail.com", 1L);
		
		String jsonBody = objectMapper.writeValueAsString(dto);
		
		ResultActions result =
				mockMvc.perform(post("/employees")
					.content(jsonBody)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated()); //objeto foi criado e retorna código 201
		result.andExpect(jsonPath("$.id").exists()); //id existe?
		result.andExpect(jsonPath("$.name").value("Joaquim")); //verificar nome
		result.andExpect(jsonPath("$.email").value("joaquim@gmail.com")); //verificar email
		result.andExpect(jsonPath("$.departmentId").value(1L)); //verificar id do departamento
	}	
}