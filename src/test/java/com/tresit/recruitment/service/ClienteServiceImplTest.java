package com.tresit.recruitment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.tresit.recruitment.RecruitmentApplication;
import com.tresit.recruitment.model.Cliente;
import com.tresit.recruitment.repository.ClienteRepository;
import com.tresit.recruitment.service.impl.ClienteServiceImpl;

@SpringBootTest(classes = RecruitmentApplication.class)
public class ClienteServiceImplTest {

	private static final long ID = 1L;
	private static final String NOMBRE = "3it";
	private static final String CASAMATRIZ = "Banco x";

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private ClienteServiceImpl clienteService;

	private Cliente cliente;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		cliente = new Cliente();

		cliente.setId(ID);
		cliente.setNombre(NOMBRE);
		cliente.setCasaMatriz(CASAMATRIZ);
	}

	@Test
	void testObtenerCliente() throws Exception {
		when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
		assertThat(clienteService.obtenerClientes()).isNotNull();
		assertThat(clienteService.obtenerClientes()).size().isEqualTo(1);

		clienteService.obtenerClientes();
	}

	@Test
	void testAgregarCliente() throws Exception {
		when(clienteRepository.save(any())).thenReturn((cliente));
		assertThat(clienteService.agregarCliente(cliente)).isNotNull();
		assertThat(clienteService.agregarCliente(cliente)).isInstanceOf(Cliente.class);

		clienteService.agregarCliente(cliente);
	}

	@Test
	void testEliminarClienteOk() throws Exception {
		clienteService.eliminarCliente(ID);
		verify(clienteRepository).deleteById(ID);
		assertThat(clienteService.eliminarCliente(ID)).isEqualTo(1);
	}

	@Test
	void testEditarCliente() {
		when(clienteRepository.findById(ID)).thenReturn(Optional.of(cliente));
		cliente.setNombre("3ittt");
		cliente.setCasaMatriz("banco YYX");
		when(clienteRepository.save(any())).thenReturn(cliente);
		assertThat(clienteService.editarCliente(cliente).getNombre()).isNotEqualTo(NOMBRE);
		assertThat(clienteService.editarCliente(cliente).getCasaMatriz()).isNotEqualTo(CASAMATRIZ);

		clienteService.editarCliente(cliente);
	}

	@Test
	void testObtenerClientePorIdOk() {
		when(clienteRepository.findById(ID)).thenReturn(Optional.of(cliente));
		assertThat(clienteService.obtenerClientePorId(ID)).isNotNull();
		assertThat(clienteService.obtenerClientePorId(ID)).isInstanceOf(Cliente.class);

		clienteService.obtenerClientePorId(ID);
	}
	
}
