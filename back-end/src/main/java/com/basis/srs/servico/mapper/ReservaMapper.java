package com.basis.srs.servico.mapper;


import com.basis.srs.dominio.Reserva;
import com.basis.srs.servico.dto.ReservaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface ReservaMapper extends EntityMapper<ReservaDTO, Reserva> {

}
