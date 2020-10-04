package com.basis.srs.repositorio;

import com.basis.srs.dominio.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//autor = "lucas.costa"
public interface ReservaRepositorio extends JpaRepository<Reserva, Integer>{
}
