package com.basis.srs.repositorio;

import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.dominio.SalaEquipamentoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SalaEquipamentoRepositorio extends JpaRepository<SalaEquipamento, SalaEquipamentoKey> {
    void deleteAllBySalaId(Integer id);

    boolean existsByEquipamentoId(Integer id);
}
