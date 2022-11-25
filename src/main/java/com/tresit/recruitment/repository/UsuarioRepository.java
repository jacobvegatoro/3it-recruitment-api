package com.tresit.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tresit.recruitment.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
