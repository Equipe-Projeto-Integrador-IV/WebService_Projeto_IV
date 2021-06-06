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
import com.projetoic.senac.model.Cliente;
import com.projetoic.senac.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	private final ClienteRepository repository;
	
	@Autowired
	public ClienteController(ClienteRepository repository) {		
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		
		return repository.save(cliente);
	}
	
	@GetMapping("{cpf}")
	public Cliente acharPorId(@PathVariable Long cpf) {
		
		return repository
				.findById(cpf)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	
	@DeleteMapping("{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCliente(@PathVariable Long cpf) {
		
		repository
		.findById(cpf)
		.map( cliente -> {
			repository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado")); 
	}
	
	
	@PutMapping("{cpf}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarCliente(@PathVariable Long cpf, @RequestBody @Valid Cliente clienteAtualizado) {
		
		repository
		.findById(cpf)
		.map( cliente -> {
			clienteAtualizado.getCpf();
			return repository.save(clienteAtualizado);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		
	}

}
