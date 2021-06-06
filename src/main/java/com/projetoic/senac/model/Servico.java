package com.projetoic.senac.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class Servico {
	
	@Id
	private Long id;
	@Column
	private String nome;
	@Column
	private String descricao;
	@Column
	private Float precoCusto;
	@Column
	private Float precoVenda;

}
