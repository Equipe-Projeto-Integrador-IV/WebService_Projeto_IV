package com.projetoic.senac.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class OrdemServico {
	
	@Id
	@NotNull(message="{campo.id.obrigatorio}")
	private Long id_os;
	
	@Column
	private String data_inicio;
	
	@Column
	private String data_fim;
	
	@Column
	private String hora_inicio;
	
	@Column
	private String hora_fim;
	
	@Column
	private String status;
	
	@Column
	private Float valor;
	
	@ManyToOne
	@NotNull(message="{campo.fk.obrigatorio}")
	@JoinColumn(name="id_agendamento_fk")
	private Agendamento agendamento;
	
	@ManyToOne
	@NotNull(message="{campo.fk.obrigatorio}")
	@JoinColumn(name="id_servico_fk")
	private Servico servico;
	
	@ManyToOne
	@NotNull(message="{campo.fk.obrigatorio}")
	@JoinColumn(name="cpf_funcionario_fk_os")
	private Funcionario respOS;
	
	@ManyToOne
	@NotNull(message="{campo.fk.obrigatorio}")
	@JoinColumn(name="cpf_funcionario_fk_servico")
	private Funcionario execServico;
	
	

}
