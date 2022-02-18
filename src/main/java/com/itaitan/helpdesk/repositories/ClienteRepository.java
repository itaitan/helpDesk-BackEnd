package com.itaitan.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itaitan.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
