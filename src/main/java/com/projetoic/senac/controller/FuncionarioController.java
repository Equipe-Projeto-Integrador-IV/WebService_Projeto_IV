package com.projetoic.senac.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.projetoic.senac.model.Funcionario;
import com.projetoic.senac.model.repository.FuncionarioRepository;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {
	
	private final FuncionarioRepository repository;
	
	@Autowired
	public FuncionarioController(FuncionarioRepository repository) {
		this.repository=repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario salvar(@RequestBody @Valid Funcionario funcionario) {
		
		return repository.save(funcionario);
	}
	
	@GetMapping("{cpf}")
	public Funcionario acharPorId(@PathVariable Long cpf) {
		
		return repository
				.findById(cpf)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
	}
	
	@DeleteMapping("{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarFuncionario(@PathVariable Long cpf) {
		
		repository
		.findById(cpf)
		.map( funcionario -> {
			repository.delete(funcionario);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado")); 
	}
	
	
	@PutMapping("{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarFuncionario(@PathVariable Long cpf, @RequestBody @Valid Funcionario funcionarioAtualizado) {
		
		repository
		.findById(cpf)
		.map( funcionario -> {
			funcionarioAtualizado.getCpf_funcionario();
			return repository.save(funcionarioAtualizado);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
		
	}

}
