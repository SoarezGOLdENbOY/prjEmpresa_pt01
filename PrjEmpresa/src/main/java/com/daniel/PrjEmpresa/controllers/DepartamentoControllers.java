package com.daniel.PrjEmpresa.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.PrjEmpresa.entities.Departamento;
import com.daniel.PrjEmpresa.services.DepartamentoServices;

@RestController
@RequestMapping("/departamento")
public class DepartamentoControllers {
		private final DepartamentoServices departamentoServices;

		public DepartamentoControllers(DepartamentoServices departamentoServices) {
			this.departamentoServices = departamentoServices;
		}

		@PostMapping
		public Departamento createdepartamento(@RequestBody Departamento departamento) {
			return departamentoServices.saveDepartamento(departamento);
		}
	  
		@GetMapping("/{id}")
		public ResponseEntity<Departamento> getDepartamento(@PathVariable Long id) {
			Departamento departamento = departamentoServices.getDepartamentoById(id);
			if (departamento != null) {
				return ResponseEntity.ok(departamento);
			} else {
				return ResponseEntity.notFound().build();
			}
		}

		// Utilizando o ResponseEntity e RequestEntity
		@GetMapping
		public ResponseEntity<List<Departamento>> getAllDepartamento(RequestEntity<Void> requestEntity) {
			String method = requestEntity.getMethod().name();
			String contentType = requestEntity.getHeaders().getContentType().toString();
			List<Departamento> departamento = departamentoServices.getAllDepartamento();
			return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
					.body(departamento);
		}

		@PutMapping("/{id}")
		public Departamento updateDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
			return departamentoServices.updateDepartamento(id, departamento);
		}

		@DeleteMapping("/{id}")
		public void deleteDepartamento(@PathVariable Long id) {
			departamentoServices.deleteDepartamento(id);
		}

	}

