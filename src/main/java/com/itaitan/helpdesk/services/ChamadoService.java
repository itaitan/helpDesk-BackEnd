package com.itaitan.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itaitan.helpdesk.domain.Chamado;
import com.itaitan.helpdesk.domain.Cliente;
import com.itaitan.helpdesk.domain.Tecnico;
import com.itaitan.helpdesk.domain.dtos.ChamadoDTO;
import com.itaitan.helpdesk.domain.enums.Prioridade;
import com.itaitan.helpdesk.domain.enums.Status;
import com.itaitan.helpdesk.repositories.ChamadoRepository;
import com.itaitan.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {
	@Autowired
	private ChamadoRepository repository;
	
	@Autowired 
	private TecnicoService tecnicoService;
	
	@Autowired 
	private ClienteService clienteService;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
	}
	
	public List<Chamado>findAll(){
		return repository.findAll();
	}

	public Chamado create(@Valid ChamadoDTO objDTO) {
		
		return repository.save(newChamado(objDTO));
	}
	
	private Chamado newChamado(ChamadoDTO obj) {
		
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Chamado chamado = new Chamado();
		
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacao(obj.getObservacao());
		
		return chamado;
	}
}
