package com.tresit.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tresit.recruitment.model.Celula;
import com.tresit.recruitment.repository.CelulaRepository;
import com.tresit.recruitment.service.CelulaService;

@Service
public class CelulaServiceImpl  implements CelulaService {
	
	@Autowired
	private CelulaRepository celulaRepositorio;
	
	@Override
	public List<Celula> obtenerCelulas() {
		return celulaRepositorio.findAll();		
	}
	
	@Override
	public Celula agregarCelula(Celula celula) {
		return celulaRepositorio.save(celula);
	}
	
	@Override
	public int eliminarCelula(Long idcelula) {
		int resultado = 0;
		try {
			celulaRepositorio.deleteById(idcelula);
			resultado = 1;
	}	catch(Exception e) {
		System.out.println(e.getMessage());
	}
		return resultado;
	}
	
	@Override
	public Celula editarCelula(Celula celula) {
		return celulaRepositorio.save(celula);
	}
	
	@Override
	public Celula obtenerCelulaPorId(Long idcelula) {
		return celulaRepositorio.findById(idcelula).get();
		}
	

}
