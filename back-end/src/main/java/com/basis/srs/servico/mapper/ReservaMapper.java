package com.basis.srs.servico.mapper;


import com.basis.srs.dominio.Reserva;
import com.basis.srs.dominio.ReservaEquipamento;
import com.basis.srs.servico.dto.ReservaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ReservaEquipamentoMapper.class})
public interface ReservaMapper extends EntityMapper<ReservaDTO, Reserva> {

    @Override
    @Mapping(source = "idCliente", target = "cliente.id")
    @Mapping(source = "idSala", target = "sala.id")
    Reserva toEntity(ReservaDTO dto);

    @Override
    @Mapping(target = "idCliente", source = "cliente.id")
    @Mapping(target = "idSala", source = "sala.id")
    ReservaDTO toDto(Reserva entity);
}
