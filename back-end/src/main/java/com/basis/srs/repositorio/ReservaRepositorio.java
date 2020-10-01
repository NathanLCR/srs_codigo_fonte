package com.basis.srs.repositorio;

import com.basis.srs.dominio.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer>{
}
