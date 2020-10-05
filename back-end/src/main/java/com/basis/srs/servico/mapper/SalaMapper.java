package com.basis.srs.servico.mapper;

import com.basis.srs.dominio.Sala;
import com.basis.srs.servico.dto.SalaDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface SalaMapper extends EntityMapper<SalaDto, Sala>{

}
