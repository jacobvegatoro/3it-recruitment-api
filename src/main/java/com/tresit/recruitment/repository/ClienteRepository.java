package com.tresit.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tresit.recruitment.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
