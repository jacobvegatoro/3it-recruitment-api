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
import com.tresit.recruitment.model.Celula;
import com.tresit.recruitment.repository.CelulaRepository;
import com.tresit.recruitment.service.impl.CelulaServiceImpl;

@SpringBootTest(classes = RecruitmentApplication.class)
public class CelulaServiceImplTest {
	
	private static final long ID = 1L;
	private static final String NOMBRE = "3it";
	
	@Mock
	private CelulaRepository celulaRepository;
	
	@InjectMocks
	private CelulaServiceImpl celulaService;
	
	private Celula celula;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		celula = new Celula();
		
		celula.setId(ID);
		celula.setNombre(NOMBRE);
	}
	
	@Test
	void testObtenerCelulas() throws Exception {
		when(celulaRepository.findAll()).thenReturn(Arrays.asList(celula));
		assertThat(celulaService.obtenerCelulas()).isNotNull();
		assertThat(celulaService.obtenerCelulas()).size().isEqualTo(1);

		celulaService.obtenerCelulas();
	}

	@Test
	void testAgregarCelula() throws Exception {
		when(celulaRepository.save(any())).thenReturn((celula));
		assertThat(celulaService.agregarCelula(celula)).isNotNull();
		assertThat(celulaService.agregarCelula(celula)).isInstanceOf(Celula.class);

		celulaService.agregarCelula(celula);
	}

	@Test
	void testEliminarCelulaOk() throws Exception {
		celulaService.eliminarCelula(ID);
		verify(celulaRepository).deleteById(ID);
		assertThat(celulaService.eliminarCelula(ID)).isEqualTo(1);
	}

	@Test
	void testEditarCelula() {
		when(celulaRepository.findById(ID)).thenReturn(Optional.of(celula));
		celula.setNombre("3it");
		when(celulaRepository.save(any())).thenReturn(celula);
		assertThat(celulaService.editarCelula(celula).getNombre()).isNotEqualTo(NOMBRE);
		
		celulaService.editarCelula(celula);
	}

	@Test
	void testObtenerCelulaPorIdOk() {
		when(celulaRepository.findById(ID)).thenReturn(Optional.of(celula));
		assertThat(celulaService.obtenerCelulaPorId(ID)).isNotNull();
		assertThat(celulaService.obtenerCelulaPorId(ID)).isInstanceOf(Celula.class);

		celulaService.obtenerCelulaPorId(ID);
	}
	
	
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
	
}
