package com.basis.srs.repositorio;

import com.basis.srs.dominio.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepositorio extends JpaRepository<Equipamento,Integer> {
}
