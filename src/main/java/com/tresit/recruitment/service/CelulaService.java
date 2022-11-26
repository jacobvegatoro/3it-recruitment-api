package com.tresit.recruitment.service;

import java.util.List;
import com.tresit.recruitment.model.Celula;

public interface CelulaService {
	
	public List<Celula> obtenerCelulas();
	public Celula agregarCelula(Celula celula);
	public int eliminarCelula(Long idcelula);
	public Celula editarCelula(Celula celula);
	public Celula obtenerCelulaPorId(Long idcelula);

}
