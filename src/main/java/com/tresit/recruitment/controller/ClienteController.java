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

import com.tresit.recruitment.model.Cliente;
import com.tresit.recruitment.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteServicio;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> listado(){
		System.out.println("Listado de clientes");
		return clienteServicio.obtenerClientes();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente agregar(@RequestBody Cliente cliente){
		return clienteServicio.agregarCliente(cliente);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente obtenerPorId(@PathVariable Long id){
		return clienteServicio.obtenerClientePorId(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente editar(@RequestBody Cliente clienteModificado, @PathVariable Long id) {
		Cliente client = clienteServicio.obtenerClientePorId(id);
		client.setNombre(clienteModificado.getNombre());
		client.setCasaMatriz(clienteModificado.getCasaMatriz());
		return clienteServicio.agregarCliente(client);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public int eliminar(@PathVariable Long id) {
		return clienteServicio.eliminarCliente(id);
	}
}