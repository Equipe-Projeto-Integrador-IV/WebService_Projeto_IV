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
import com.projetoic.senac.model.Agendamento;
import com.projetoic.senac.model.repository.AgendamentoRepository;




@RestController
@RequestMapping("/api/agendamento")
public class AgendamentoController {
	
	private final AgendamentoRepository repository;
	
	@Autowired
	public AgendamentoController(AgendamentoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Agendamento salvarAgendamento(@RequestBody @Valid Agendamento agendamento) {
		
		return repository.save(agendamento);
	}
	
	@GetMapping("{id}")
	public Agendamento acharPorId(@PathVariable Long id) {
		
		return repository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarAgendamento(@PathVariable Long id) {
		
		repository
		.findById(id)
		.map( agendamento -> {
			repository.delete(agendamento);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado")); 
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarAgendamento(@PathVariable Long id, @RequestBody @Valid Agendamento agendamentoAtualizado) {
		
		repository
		.findById(id)
		.map( agendamento -> {
			agendamentoAtualizado.getId();
			return repository.save(agendamentoAtualizado);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
		
	}
	
	

}
