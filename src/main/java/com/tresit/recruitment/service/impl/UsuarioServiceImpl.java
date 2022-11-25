package com.tresit.recruitment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tresit.recruitment.model.Usuario;
import com.tresit.recruitment.repository.UsuarioRepository;
import com.tresit.recruitment.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	@Override
	public List<Usuario> obtenerUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario agregarUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public int eliminarUsuario(long idusuario) {
		int resultado = 0;
		try {
			usuarioRepositorio.deleteById(idusuario);
			resultado = 1;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}

	@Override
	public Usuario editarUsuario(Usuario usuario) {
		return usuarioRepositorio.save(usuario);
	}

	@Override
	public Usuario obtenerUsuarioPorId(long idusuario) {
		return usuarioRepositorio.findById(idusuario).get();
	}

}
