package com.projetoic.senac.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Long id;
	
	@Column
	private Date dataHoraInicio;
	
	@Column
	private Date dataHoraTermino;
	
	@Column
	private String status;
	
	@Column
	private Float valor;
	
	@ManyToOne
	@JoinColumn(name="id_agendamento")
	private Agendamento agendamento;
	
	@ManyToOne
	@JoinColumn(name="id_servico")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario resOS;
	
	@ManyToOne
	@JoinColumn(name="id_funcionario")
	private Funcionario execServico;
	
	

}
