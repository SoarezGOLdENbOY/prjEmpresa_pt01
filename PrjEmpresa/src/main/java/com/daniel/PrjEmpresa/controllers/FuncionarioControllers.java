package com.daniel.PrjEmpresa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.PrjEmpresa.entities.Funcionario;
import com.daniel.PrjEmpresa.services.FuncionarioServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioControllers {
	private final FuncionarioServices funcionarioServices;

	@Autowired
		public FuncionarioControllers(FuncionarioServices funcionarioServices) {
			this.funcionarioServices = funcionarioServices;
		}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> findUsuariobyId(@PathVariable Long id) {
		Funcionario funcionario = funcionarioServices.getFuncionarioById(id);
		if (funcionario != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Funcionario>> findAllFuncionariocontrol() {
		List<Funcionario> funcionario = funcionarioServices.getAllFuncionario();
		return ResponseEntity.ok(funcionario);
	}

	@PostMapping
	public ResponseEntity<Funcionario> insertFuncionarioControl(@RequestBody @Valid Funcionario funcionario) {
		Funcionario novoFuncionario = funcionarioServices.saveFuncionario(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
	}

	@PutMapping("/id")
	public ResponseEntity<Funcionario> updateFuncionarioControl(@PathVariable Long id, @RequestBody @Valid Funcionario funcionario) {
		Funcionario mudafuncionario = funcionarioServices.updateFuncionario(id, funcionario);
		if (mudafuncionario != null) {
			return ResponseEntity.ok(funcionario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/id")
	public ResponseEntity<String> deleteUsuarioControl(@PathVariable Long id) {
		boolean remover = funcionarioServices.deleteFuncionario(id);
		if (remover) {
			return ResponseEntity.ok().body("Funcionario Excluido com sucesso");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
