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

import com.tresit.recruitment.model.Usuario;
import com.tresit.recruitment.service.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServicio;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> listado(){
		System.out.println("Listado de usuarios");
		return usuarioServicio.obtenerUsuarios();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario agregar(@RequestBody Usuario usuario) {
		return usuarioServicio.agregarUsuario(usuario);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario obtenerPorId(@PathVariable Long id){
		return usuarioServicio.obtenerUsuarioPorId(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Usuario editar(@RequestBody Usuario usuarioModificado, @PathVariable Long id) {
		Usuario user = usuarioServicio.obtenerUsuarioPorId(id);
		user.setNombre(usuarioModificado.getNombre());
		user.setApellido(usuarioModificado.getApellido());
		user.setLogin(usuarioModificado.getLogin());
		user.setClave(usuarioModificado.getClave());
		user.setCorreo(usuarioModificado.getCorreo());
		user.setTelefono(usuarioModificado.getTelefono());
		return usuarioServicio.agregarUsuario(user);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public int eliminar(@PathVariable Long id) {
		return usuarioServicio.eliminarUsuario(id);
	}
}
