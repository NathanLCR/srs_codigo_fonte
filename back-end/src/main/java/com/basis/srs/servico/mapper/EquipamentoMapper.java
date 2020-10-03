package com.basis.srs.servico.mapper;

import com.basis.srs.dominio.Equipamento;
import com.basis.srs.servico.dto.EquipamentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface EquipamentoMapper extends EntityMapper<EquipamentoDTO, Equipamento>{
}
