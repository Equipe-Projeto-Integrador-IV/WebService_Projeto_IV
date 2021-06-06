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
import com.projetoic.senac.model.OrdemServico;
import com.projetoic.senac.model.repository.OrdemServicoRepository;





@RestController
@RequestMapping("/api/ordemservico")
public class OrdemServicoController {
	
	
	private final OrdemServicoRepository repository;
	
	@Autowired
	public OrdemServicoController(OrdemServicoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServico salvarOrdemServico(@RequestBody @Valid OrdemServico ordemServico) {
		
		return repository.save(ordemServico);
	}
	
	@GetMapping("{id}")
	public OrdemServico acharPorId(@PathVariable Long id) {
		
		return repository
				.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarOrdemServico(@PathVariable Long id) {
		
		repository
		.findById(id)
		.map( ordemServico -> {
			repository.delete(ordemServico);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrado")); 
	}
	
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarOrdemServico(@PathVariable Long id, @RequestBody @Valid OrdemServico ordemServicoAtualizado) {
		
		repository
		.findById(id)
		.map( ordemServico -> {
			ordemServicoAtualizado.getId();
			return repository.save(ordemServicoAtualizado);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrado"));
		
	}

}
