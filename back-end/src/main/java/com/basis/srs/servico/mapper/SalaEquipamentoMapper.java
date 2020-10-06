package com.basis.srs.servico.mapper;

import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.servico.dto.SalaEquipamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {})
public interface SalaEquipamentoMapper extends EntityMapper<SalaEquipamentoDTO, SalaEquipamento> {

    @Override
    @Mapping(source = "sala", target = "sala.id")
    @Mapping(source = "equipamento", target = "equipamento.id")
    @Mapping(source = "sala", target = "id.idSala")
    @Mapping(source = "equipamento", target = "id.idEquipamento")
    SalaEquipamento toEntity(SalaEquipamentoDTO salaEquipamentoDTO);

    @Override
    @Mapping(target = "sala", source = "sala.id")
    @Mapping(target = "equipamento", source = "equipamento.id")
    SalaEquipamentoDTO toDto(SalaEquipamento salaEquipamento);
}




