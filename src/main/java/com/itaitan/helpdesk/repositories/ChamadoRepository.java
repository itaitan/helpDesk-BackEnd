package com.itaitan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itaitan.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{

}
