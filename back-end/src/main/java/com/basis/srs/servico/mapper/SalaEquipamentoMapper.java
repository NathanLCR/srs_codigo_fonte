package com.basis.srs.servico.mapper;

import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.servico.dto.SalaEquipamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {})
public interface SalaEquipamentoMapper extends EntityMapper<SalaEquipamentoDTO, SalaEquipamento> {

    @Override
    @Mapping(source = "idSala", target = "id.idSala")
    @Mapping(source = "idEquipamento", target = "id.idEquipamento")
    @Mapping(target = "Sala.id", source = "idSala")
    @Mapping(target = "Equipamento.id", source = "idEquipamento")
    SalaEquipamento toEntity(SalaEquipamentoDTO salaEquipamentoDTO);

    @Override
    @Mapping(target = "id.idSala", source = "idSala")
    @Mapping(target = "id.idEquipamento", source = "idEquipamento")
    SalaEquipamentoDTO toDto(SalaEquipamento salaEquipamento);
}




