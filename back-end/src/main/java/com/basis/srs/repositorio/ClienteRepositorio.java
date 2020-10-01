package com.basis.srs.repositorio;

import com.basis.srs.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

}
