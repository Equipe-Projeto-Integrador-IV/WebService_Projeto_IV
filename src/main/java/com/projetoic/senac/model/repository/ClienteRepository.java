package com.projetoic.senac.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoic.senac.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
