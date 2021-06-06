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
import com.projetoic.senac.model.Servico;
import com.projetoic.senac.model.repository.ServicoRepository;


@RestController
@RequestMapping("/api/servico")
public class ServicoController {
	
	private final ServicoRepository repository;
	
	@Autowired
	ServicoController(ServicoRepository repository){
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico salvarServico(@RequestBody @Valid Servico servico) {
		
		return repository.save(servico);
	}
	
	@GetMapping("{id}")
	public Servico acharPorId(@PathVariable Long id) {
		
		return repository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarServico(@PathVariable Long id) {
		
		repository
		.findById(id)
		.map( servico -> {
			repository.delete(servico);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado")); 
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarServico(@PathVariable Long id, @RequestBody @Valid Servico servicoAtualizado) {
		
		repository
		.findById(id)
		.map( funcionario -> {
			servicoAtualizado.getId();
			return repository.save(servicoAtualizado);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
		
	}

}
