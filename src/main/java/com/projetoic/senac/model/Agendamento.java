package com.projetoic.senac.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
	
	@Id
	private Long id;
	
	@Column
	private LocalDate data;
	
	@Column
	private LocalTime hora;
	
	@Column
	private String status;
	
	@Column
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="cpf_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="cpf_funcionario")
	private Funcionario respAgendamento;
	
	@ManyToOne
	@JoinColumn(name="id_servico")
	private Servico servico;
	
	@PrePersist
	public void prePersistData() {
		
		setData(LocalDate.now());
	}
	
	
	public void prePersistHora() {
		
		setHora(LocalTime.now());
	}

}
