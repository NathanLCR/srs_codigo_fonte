package com.basis.srs.servico.mapper;

import com.basis.srs.dominio.Sala;
import com.basis.srs.servico.dto.SalaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SalaEquipamentoMapper.class})
public interface SalaMapper extends EntityMapper<SalaDTO, Sala>{

    @Override
    @Mapping(source = "idTipoSala", target = "tipoSala.id")
    Sala toEntity(SalaDTO dto);

    @Override
    @Mapping(target = "idTipoSala", source = "tipoSala.id")
    SalaDTO toDto(Sala sala);
}
