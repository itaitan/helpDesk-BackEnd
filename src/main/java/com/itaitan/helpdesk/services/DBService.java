package com.itaitan.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itaitan.helpdesk.domain.Chamado;
import com.itaitan.helpdesk.domain.Cliente;
import com.itaitan.helpdesk.domain.Tecnico;
import com.itaitan.helpdesk.domain.enums.Perfil;
import com.itaitan.helpdesk.domain.enums.Prioridade;
import com.itaitan.helpdesk.domain.enums.Status;
import com.itaitan.helpdesk.repositories.ChamadoRepository;
import com.itaitan.helpdesk.repositories.ClienteRepository;
import com.itaitan.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;	

	public void intenciaDb() {
		Tecnico tec1 = new Tecnico(null, "Itaitan", "238.184.420-10", "itaitan@gmail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Sabino", "836.527.220-27", "sabino@gmail.com", "321");
		cli1.addPerfil(Perfil.CLIENTE);
		
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamdo", tec1, cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
