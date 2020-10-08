package com.basis.srs.repositorio;

import com.basis.srs.dominio.Sala;
import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.dominio.SalaEquipamentoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaEquipamentoRepositorio extends JpaRepository<SalaEquipamento, SalaEquipamentoKey> {
    void deleteAllBySalaId(Integer id);

    boolean existsByEquipamentoId(Integer id);
}
