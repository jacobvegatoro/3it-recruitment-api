package com.tresit.recruitment.service;

import java.util.List;

import com.tresit.recruitment.model.Usuario;

public interface UsuarioService {

	public List<Usuario> obtenerUsuarios();
	public Usuario agregarUsuario(Usuario usuario);
	public int eliminarUsuario(long idusuario);
	public Usuario editarUsuario(Usuario usuario);
	public Usuario obtenerUsuarioPorId(long idusuario);

}
