package com.devsuperior.bds01.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class DepartmentControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	//02-41 Implementando o desafio resolvido - até os 10:00 minutos
	@Test //testando a classe DepartmentControllerIT, equivalente a DepartmentResource
	public void findAllShouldReturnAllResourcesSortedByName() throws Exception {
		
		ResultActions result =
				mockMvc.perform(get("/departments") //busca todos os departamentos, espera uma lista não paginada
					.contentType(MediaType.APPLICATION_JSON)); //O SERVICE DEVE RETORNAR OS DEPARTAMENTOS

		result.andExpect(status().isOk()); //código 200
		//espera um objeto que na verdade é uma lista, retorna os departamentos ordenados por nome
		result.andExpect(jsonPath("$[0].name").value("Management")); //espera uma lista não paginada
		result.andExpect(jsonPath("$[1].name").value("Sales")); //espera uma lista não paginada
		result.andExpect(jsonPath("$[2].name").value("Training")); //espera uma lista não paginada
	}
}
