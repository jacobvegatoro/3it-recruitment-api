package com.tresit.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tresit.recruitment.model.Cliente;
import com.tresit.recruitment.repository.ClienteRepository;
import com.tresit.recruitment.service.ClienteService;

@Service
public class ClienteServiceImpl  implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepositorio;
	
	@Override
	public List<Cliente> obtenerClientes() {
		return clienteRepositorio.findAll();		
	}
	
	@Override
	public Cliente agregarCliente(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	@Override
	public int eliminarCliente(Long idcliente) {
		int resultado = 0;
		try {
			clienteRepositorio.deleteById(idcliente);
			resultado = 1;
	}	catch(Exception e) {
		System.out.println(e.getMessage());
	}
		return resultado;
	}
	
	@Override
	public Cliente editarCliente(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}
	
	@Override
	public Cliente obtenerClientePorId(Long idcliente) {
		return clienteRepositorio.findById(idcliente).get();
		}
	

}
