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
import com.tresit.recruitment.model.Celula;
import com.tresit.recruitment.service.CelulaService;

@SpringBootTest(classes = RecruitmentApplication.class)
public class CelulaControllerTest {
	
	private Celula celula1;
	private Celula celula2;
	
	private static final Long ID = 1L;
	
	private static final String NOMBRE = "3it";
//	private static final String NOMBRE = "dragpharma";
	
	private static final String NOMBRE2 = "Anam";
//  private static final String NOMBRE2 = "Viamed laboratory"
	
	private static final List<Celula> LISTA_CELULAS = new ArrayList<>();
	
	@Mock
	private CelulaService celulaService;
	
	@InjectMocks
	private CelulaController celulaController;
	
	@BeforeEach
	void setUp () throws Exception {
		MockitoAnnotations.openMocks(this);
		
		celula1 = new Celula();
		celula2 = new Celula();
		
		celula1.setId(ID);
		celula1.setNombre(NOMBRE);
		
		celula2.setId(ID);
		celula2.setNombre(NOMBRE2);
		
		when(celulaService.obtenerCelulaPorId(ID)).thenReturn(celula1);
		when(celulaService.eliminarCelula(ID)).thenReturn(1);
		when(celulaService.agregarCelula(celula1)).thenReturn(celula1);
		when(celulaService.editarCelula(celula1)).thenReturn(celula2);
	}
	@Test
	void testListCelulas() {
		
		final List<Celula> celulas = celulaController.listado();
		assertThat(celulas).isEqualTo(LISTA_CELULAS);
	}
	
	@Test
	void testCelulaPorId() {
		final Celula celula = celulaController.obtenerPorId(ID);
		assertThat(celula).isNotNull();
		assertThat(celula).isInstanceOf(Celula.class);
		assertThat(celula.getNombre()).isEqualTo(NOMBRE);
	}
	
	@Test
	void testAgregarNuevoCelula() {
		Celula celulaN = celula1;
		final Celula celula = celulaController.agregar(celulaN);
		assertThat(celula).isNotNull();
		assertThat(celula).isInstanceOf(Celula.class);
		assertThat(celula.getNombre()).isEqualTo(NOMBRE);
		
	}
	
	@Test
	void testModificarCelula() {
		final Celula celula = celulaController.obtenerPorId(ID);
		final Celula celulaMod = celulaController.editar(celula2, celula.getId());
		assertThat(celulaMod.getNombre()).isNotEqualTo(NOMBRE);
	}
	
	@Test
	void testBorrarCelula() {
		final int celula = celulaController.eliminar(ID);
		assertThat(celula).isEqualTo(1);
	}
	
	

}
