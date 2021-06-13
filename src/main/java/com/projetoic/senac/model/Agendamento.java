package com.projetoic.senac.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

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
	@NotNull(message="{campo.id.obrigatorio}")
	private Long id;
	
	@Column
	private String data;
	//private LocalDate data;
	
	@Column
	private String hora;
	//private LocalTime hora;
	
	@Column(nullable = false, length = 20)
	private String status;
	
	@Column(nullable = false, length = 150)
	private String obs;
	
	@ManyToOne
	@NotNull(message="{campo.fk.obrigatorio}")
	@JoinColumn(name="cpf_cliente_fk_agendamento")
	private Cliente cliente;
	
	@ManyToOne
	@NotNull(message="{campo.fk.obrigatorio}")
	@JoinColumn(name="cpf_funcionario_fk_agendamento")
	private Funcionario respAgendamento;
	
	@ManyToOne
	@NotNull(message="{campo.fk.obrigatorio}")
	@JoinColumn(name="id_servico_fk_agendamento")
	private Servico servico;
	
	
	/*
	@PrePersist
	public void prePersistData() {
		
		setData(LocalDate.now());
		setHora(LocalTime.now());
	}
	*/
	
	

}
