package com.basis.srs.repositorio;


import com.basis.srs.dominio.Sala;
import org.springframework.data.jpa.repository.JpaRepository ;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepositorio extends JpaRepository <Sala, Integer>{
}
