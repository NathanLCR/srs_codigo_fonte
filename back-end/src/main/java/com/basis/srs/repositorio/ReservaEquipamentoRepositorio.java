package com.basis.srs.repositorio;

import com.basis.srs.dominio.ReservaEquipamento;
import com.basis.srs.dominio.ReservaEquipamentoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaEquipamentoRepositorio extends JpaRepository<ReservaEquipamento, ReservaEquipamentoKey> {
    void deleteAllByReservaId(Integer id);

    boolean existsByEquipamentoId(Integer id);
}
