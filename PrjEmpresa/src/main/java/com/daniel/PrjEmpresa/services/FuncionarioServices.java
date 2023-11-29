package com.daniel.PrjEmpresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.daniel.PrjEmpresa.entities.Funcionario;
import com.daniel.PrjEmpresa.repository.FuncionarioRepository;

@Service
public class FuncionarioServices {

	private final FuncionarioRepository funcionarioRepository;

	public FuncionarioServices(FuncionarioRepository funcionarioRepository) {
			this.funcionarioRepository = funcionarioRepository;
		}

	public Funcionario getFuncionarioById(Long id) {
		return funcionarioRepository.findById(id).orElse(null);

	}

	public Funcionario saveFuncionario(Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

	public List<Funcionario> getAllFuncionario() {
		return funcionarioRepository.findAll();
	}

	public boolean deleteFuncionario(Long id) {
		funcionarioRepository.deleteById(id);
		return false;
	}

	public Funcionario updateFuncionario(Long id, Funcionario novoFuncionario) {
		Optional<Funcionario> funcionarioOptnial = funcionarioRepository.findById(id);
		if (funcionarioOptnial.isPresent()) {
			Funcionario funcionarioExistente = funcionarioOptnial.get();
			funcionarioExistente.setFunnome(novoFuncionario.getFunnome());
			funcionarioExistente.setFuncodigo(novoFuncionario.getFuncodigo());
			return funcionarioRepository.save(funcionarioExistente);
		} else {
			return null;
		}
	}
}