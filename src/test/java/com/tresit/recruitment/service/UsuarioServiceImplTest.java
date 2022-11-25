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
import com.tresit.recruitment.model.Usuario;
import com.tresit.recruitment.repository.UsuarioRepository;
import com.tresit.recruitment.service.impl.UsuarioServiceImpl;

@SpringBootTest(classes = RecruitmentApplication.class)
public class UsuarioServiceImplTest {

	private static final long ID = 1L;
	private static final String NOMBRE = "Danilo";
	private static final String APELLIDO = "Diaz";

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioServiceImpl usuarioService;

	private Usuario usuario;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);

		usuario = new Usuario();

		usuario.setId(ID);
		usuario.setNombre(NOMBRE);
		usuario.setApellido(APELLIDO);
	}

	@Test
	void testObtenerUsuarios() throws Exception {
		when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));
		assertThat(usuarioService.obtenerUsuarios()).isNotNull();
		assertThat(usuarioService.obtenerUsuarios()).size().isEqualTo(1);

		usuarioService.obtenerUsuarios();
	}

	@Test
	void testAgregarUsuario() throws Exception {
		when(usuarioRepository.save(any())).thenReturn((usuario));
		assertThat(usuarioService.agregarUsuario(usuario)).isNotNull();
		assertThat(usuarioService.agregarUsuario(usuario)).isInstanceOf(Usuario.class);

		usuarioService.agregarUsuario(usuario);
	}

	@Test
	void testEliminarUsuarioOk() throws Exception {
		usuarioService.eliminarUsuario(ID);
		verify(usuarioRepository).deleteById(ID);
		assertThat(usuarioService.eliminarUsuario(ID)).isEqualTo(1);
	}

	@Test
	void testEditarUsuario() {
		when(usuarioRepository.findById(ID)).thenReturn(Optional.of(usuario));
		usuario.setNombre("Danilodon");
		usuario.setApellido("Diazon");
		when(usuarioRepository.save(any())).thenReturn(usuario);
		assertThat(usuarioService.editarUsuario(usuario).getNombre()).isNotEqualTo(NOMBRE);
		assertThat(usuarioService.editarUsuario(usuario).getApellido()).isNotEqualTo(APELLIDO);

		usuarioService.editarUsuario(usuario);
	}

	@Test
	void testObtenerUsuarioPorIdOk() {
		when(usuarioRepository.findById(ID)).thenReturn(Optional.of(usuario));
		assertThat(usuarioService.obtenerUsuarioPorId(ID)).isNotNull();
		assertThat(usuarioService.obtenerUsuarioPorId(ID)).isInstanceOf(Usuario.class);

		usuarioService.obtenerUsuarioPorId(ID);
	}
	
}
