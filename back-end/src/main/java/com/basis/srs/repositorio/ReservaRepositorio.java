package com.basis.srs.repositorio;

import com.basis.srs.dominio.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//autor = "lucas.costa"
public interface ReservaRepositorio extends JpaRepository<Reserva, Integer>{

    boolean existsBySalaId(Integer id);

    List<Reserva> getAllBySalaId(Integer id);


}
