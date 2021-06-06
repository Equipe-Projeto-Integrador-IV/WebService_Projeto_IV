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
public class Funcionario {
	
	@Id
	private Long cpf;
	@Column
	private String email;
	@Column
	private Long senha;
	@Column
	private String telefone;
	@Column
	private Float comissao;
	

}
