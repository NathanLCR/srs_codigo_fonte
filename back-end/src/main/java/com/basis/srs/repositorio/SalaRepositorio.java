package com.basis.srs.repositorio;


import com.basis.srs.dominio.Sala;
import org.springframework.data.jpa.repository.JpaRepository ;

public interface SalaRepositorio extends JpaRepository <Sala, Integer>{
}
