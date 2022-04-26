package com.itaitan.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.itaitan.helpdesk.domain.Pessoa;
import com.itaitan.helpdesk.domain.Tecnico;
import com.itaitan.helpdesk.domain.dtos.TecnicoDTO;
import com.itaitan.helpdesk.repositories.PessoaRepository;
import com.itaitan.helpdesk.repositories.TecnicoRepository;
import com.itaitan.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.itaitan.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> obj = repository.findById(id);	
		
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! id: " + id));
		
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCPFEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj);
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {		
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		if(!objDTO.getSenha().equals(oldObj.getSenha())) {
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		}
		validaPorCPFEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não podem ser deletados!");
		}
		
		repository.deleteById(id);
		
	}

	private void validaPorCPFEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent()&& obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF ja cadastrado no sitesma");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent()&& obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email ja cadastrado no sitesma");
		}
	}

	

	
}
