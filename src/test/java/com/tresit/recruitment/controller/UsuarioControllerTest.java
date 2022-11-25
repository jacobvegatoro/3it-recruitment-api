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
import com.tresit.recruitment.model.Usuario;
import com.tresit.recruitment.service.UsuarioService;

@SpringBootTest(classes = RecruitmentApplication.class)
public class UsuarioControllerTest {

	private Usuario usuario1;
	private Usuario usuario2;
	
	private static final long ID = 1L;

	private static final String NOMBRE = "Benito";
	private static final String APELLIDO = "Barraza";
	private static final String LOGIN = "bbarraza";
	private static final String CLAVE = "332211";
	private static final String CORREO = "bbarraza@gmail.com";
	private static final String TELEFONO = "+5699887766";
	
	private static final String NOMBRE2 = "Carlos";
	private static final String APELLIDO2 = "Carmona";
	private static final String LOGIN2 = "ccarmona";
	private static final String CLAVE2 = "443322";
	private static final String CORREO2 = "ccarmona@gmail.com";
	private static final String TELEFONO2 = "+5699776655";

	private static final List<Usuario> LISTA_USUARIOS = new ArrayList<>();

	@Mock
	private UsuarioService usuarioService;
	
	@InjectMocks
	private UsuarioController usuarioController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		usuario1 = new Usuario();
		usuario2 = new Usuario();

		usuario1.setId(ID);
		usuario1.setNombre(NOMBRE);
		usuario1.setApellido(APELLIDO);
		
		usuario2.setId(ID);
		usuario2.setNombre(NOMBRE2);
		usuario2.setApellido(APELLIDO2);

		when(usuarioService.obtenerUsuarioPorId(ID)).thenReturn(usuario1);
		when(usuarioService.eliminarUsuario(ID)).thenReturn(1);
		when(usuarioService.agregarUsuario(usuario1)).thenReturn(usuario1);
		when(usuarioService.editarUsuario(usuario2)).thenReturn(usuario2);

	}

	@Test
	void testListadoUsuarios() {

		final List<Usuario> usuarios = usuarioController.listado();
		assertThat(usuarios).isEqualTo(LISTA_USUARIOS);

	}

	@Test
	void testUsuarioPorId() {
		
		final Usuario usuario = usuarioController.obtenerPorId(ID);
		assertThat(usuario).isNotNull();
		assertThat(usuario).isInstanceOf(Usuario.class);
		assertThat(usuario.getNombre()).isEqualTo(NOMBRE);
		assertThat(usuario.getApellido()).isEqualTo(APELLIDO);
		
	}

	@Test
	void testAgregarNuevoUsuario() {
		Usuario usuarioN = usuario1;
		final Usuario usuario = usuarioController.agregar(usuarioN);
		assertThat(usuario).isNotNull();
		assertThat(usuario).isInstanceOf(Usuario.class);
		assertThat(usuario.getNombre()).isEqualTo(NOMBRE);
		assertThat(usuario.getApellido()).isEqualTo(APELLIDO);
	}

	@Test
	void testModificarUsuario() {
		final Usuario usuario = usuarioController.obtenerPorId(ID);
		final Usuario usuarioMod = usuarioController.editar(usuario2, usuario.getId());
		assertThat(usuarioMod.getNombre()).isNotEqualTo(NOMBRE);
		assertThat(usuarioMod.getApellido()).isNotEqualTo(APELLIDO);

	}

	/*@Test
	void testBorrarUsuario() {
		final int usuario = usuarioController.eliminar(ID);
		assertThat(usuario).isEqualTo(1);

	}*/	
}
