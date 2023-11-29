package com.daniel.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.daniel.PrjEmpresa.entities.Departamento;
import com.daniel.PrjEmpresa.repository.DepartamentoRepository;

@Service
 public class DepartamentoServices{
	private final DepartamentoRepository departamentoRepository;

	public DepartamentoServices(DepartamentoRepository departamentoRepository) {
		this.departamentoRepository = departamentoRepository;
	}

	public Departamento getDepartamentoById(Long id) {
		return departamentoRepository.findById(id).orElse(null);

	}

	public Departamento saveDepartamento(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}

	public List<Departamento> getAllDepartamento() {
		return departamentoRepository.findAll();
	}

	public void deleteDepartamento(Long id) {
		departamentoRepository.deleteById(id);
	}

	public Departamento updateDepartamento(Long id, Departamento novoDepartamento) {
		Optional<Departamento> departamentoOptional = departamentoRepository.findById(id);
		if (departamentoOptional.isPresent()) {
			Departamento departamentoExistente = departamentoOptional.get();
			departamentoExistente.setDepname(novoDepartamento.getDepname());
			departamentoExistente.setDepcodigo(novoDepartamento.getDepcodigo());
			return departamentoRepository.save(departamentoExistente);
		} else {
			return null;
		}
	}

}
