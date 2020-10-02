package com.basis.srs.servico.mapper;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.servico.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.EntityManager;

@Mapper(componentModel = "spring", uses = {})
public interface ClienteMapper {

    ClienteDTO toEntity(Cliente cliente);

    Cliente toDto(ClienteDTO clienteDTO);
}




