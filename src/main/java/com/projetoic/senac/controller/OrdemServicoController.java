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
	
	@GetMapping("{id_os}")
	public OrdemServico acharPorId(@PathVariable Long id_os) {
		
		return repository
				.findById(id_os)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrado"));
	}
	
	@DeleteMapping("{id_os}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarOrdemServico(@PathVariable Long id_os) {
		
		repository
		.findById(id_os)
		.map( ordemServico -> {
			repository.delete(ordemServico);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrado")); 
	}
	
	
	@PutMapping("{id_os}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarOrdemServico(@PathVariable Long id_os, @RequestBody @Valid OrdemServico ordemServicoAtualizado) {
		
		repository
		.findById(id_os)
		.map( ordemServico -> {
			ordemServicoAtualizado.getId_os();
			return repository.save(ordemServicoAtualizado);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de Serviço não encontrado"));
		
	}

}
