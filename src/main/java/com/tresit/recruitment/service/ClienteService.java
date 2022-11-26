package com.tresit.recruitment.service;

import java.util.List;
import com.tresit.recruitment.model.Cliente;

public interface ClienteService {
	
	public List<Cliente> obtenerClientes();
	public Cliente agregarCliente(Cliente cliente);
	public int eliminarCliente (Long idcliente);
	public Cliente editarCliente(Cliente cliente);
	public Cliente obtenerClientePorId(Long idcliente);
	
}
