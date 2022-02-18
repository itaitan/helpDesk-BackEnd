package com.itaitan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itaitan.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
