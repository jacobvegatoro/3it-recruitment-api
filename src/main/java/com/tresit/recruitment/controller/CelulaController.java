package com.tresit.recruitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tresit.recruitment.model.Celula;
import com.tresit.recruitment.service.CelulaService;

@RestController
@RequestMapping("/api/vl/celulas")
public class CelulaController {
	
	@Autowired
	private CelulaService celulaServicio;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Celula> listado(){
		System.out.println("Listado de celulas");
		return celulaServicio.obtenerCelulas();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Celula agregar(@RequestBody Celula celula) {
		return celulaServicio.agregarCelula(celula);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Celula obtenerPorId(@PathVariable Long id) {
		return celulaServicio.obtenerCelulaPorId(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Celula editar(@RequestBody Celula celulaModificada, @PathVariable Long id) {
		Celula cell = celulaServicio.obtenerCelulaPorId(id);
		cell.setNombre(celulaModificada.getNombre());
		return celulaServicio.agregarCelula(cell);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public int eliminar(@PathVariable Long id) {
		return celulaServicio.eliminarCelula(id);
	}

}
