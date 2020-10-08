package com.basis.srs.servico.mapper;

import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.servico.dto.SalaEquipamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {})
public interface SalaEquipamentoMapper extends EntityMapper<SalaEquipamentoDTO, SalaEquipamento> {

    @Override
    @Mapping(source = "idSala", target = "sala.id")
    @Mapping(source = "idEquipamento", target = "equipamento.id")
    @Mapping(source = "idSala", target = "id.idSala")
    @Mapping(source = "idEquipamento", target = "id.idEquipamento")
    SalaEquipamento toEntity(SalaEquipamentoDTO salaEquipamentoDTO);

    @Override
    @Mapping(target = "idSala", source = "sala.id")
    @Mapping(target = "idEquipamento", source = "equipamento.id")
    SalaEquipamentoDTO toDto(SalaEquipamento salaEquipamento);
}




