package com.basis.srs.repositorio;

import com.basis.srs.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

    boolean existsByCpf(String cpf);

}
