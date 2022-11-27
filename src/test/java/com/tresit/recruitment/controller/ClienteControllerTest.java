package com.tresit.recruitment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.tresit.recruitment.RecruitmentApplication;
import com.tresit.recruitment.model.Cliente;
import com.tresit.recruitment.service.ClienteService;

@SpringBootTest(classes = RecruitmentApplication.class)
public class ClienteControllerTest {

	private Cliente cliente1;
	private Cliente cliente2;
	
	private static final long ID = 1L;

	private static final String NOMBRE = "Alberto";
	private static final String CASAMATRIZ = "banco x";

	
	private static final String NOMBRE2 = "Federico";
	private static final String CASAMATRIZ2 = "Banco Y";

	private static final List<Cliente> LISTA_CLIENTES = new ArrayList<>();

	@Mock
	private ClienteService clienteService;
	
	@InjectMocks
	private ClienteController clienteController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		cliente1 = new Cliente();
		cliente2 = new Cliente();

		cliente1.setId(ID);
		cliente1.setNombre(NOMBRE);
		cliente1.setCasaMatriz(CASAMATRIZ);
		
		cliente2.setId(ID);
		cliente2.setNombre(NOMBRE2);
		cliente2.setCasaMatriz(CASAMATRIZ2);

		when(clienteService.obtenerClientePorId(ID)).thenReturn(cliente1);
		when(clienteService.eliminarCliente(ID)).thenReturn(1);
		when(clienteService.agregarCliente(cliente1)).thenReturn(cliente1);
		when(clienteService.editarCliente(cliente2)).thenReturn(cliente2);

	}

	@Test
	void testListadoCliente() {

		final List<Cliente> clientes = clienteController.listado();
		assertThat(clientes).isEqualTo(LISTA_CLIENTES);

	}

	@Test
	void testClientePorId() {
		
		final Cliente cliente = clienteController.obtenerPorId(ID);
		assertThat(cliente).isNotNull();
		assertThat(cliente).isInstanceOf(Cliente.class);
		assertThat(cliente.getNombre()).isEqualTo(NOMBRE);
		assertThat(cliente.getCasaMatriz()).isEqualTo(CASAMATRIZ);
		
	}

	@Test
	void testAgregarNuevoCliente() {
		Cliente clienteN = cliente1;
		final Cliente cliente = clienteController.agregar(clienteN);
		assertThat(cliente).isNotNull();
		assertThat(cliente).isInstanceOf(Cliente.class);
		assertThat(cliente.getNombre()).isEqualTo(NOMBRE);
		assertThat(cliente.getCasaMatriz()).isEqualTo(CASAMATRIZ);
	}

	@Test
	void testModificarCliente() {
		final Cliente cliente = clienteController.obtenerPorId(ID);
		final Cliente clienteMod = clienteController.editar(cliente2, cliente.getId());
		assertThat(clienteMod.getNombre()).isNotEqualTo(NOMBRE);
		assertThat(clienteMod.getCasaMatriz()).isNotEqualTo(CASAMATRIZ);

	}

	@Test
	void testBorrarcliente() {
		final int cliente = clienteController.eliminar(ID);
		assertThat(cliente).isEqualTo(1);
	}	
}