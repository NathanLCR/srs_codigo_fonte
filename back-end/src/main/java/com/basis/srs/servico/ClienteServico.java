package com.basis.srs.servico;


import com.basis.srs.dominio.Cliente;
import com.basis.srs.repositorio.ClienteRepositorio;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.servico.mapper.ClienteMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServico {

    private final ClienteRepositorio clienteRepositorio;

    private final ClienteMapper clienteMapper;

    public ClienteServico(ClienteRepositorio clienteRepositorio, ClienteMapper clienteMapper) {
        this.clienteRepositorio = clienteRepositorio;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> listar() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        List<ClienteDTO> clienteDto =  clienteMapper.toDto(clientes);
        return clienteDto;
    }

    public ClienteDTO buscarPorId(Integer id) {
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);
        ClienteDTO clienteDto = clienteMapper.toDto(cliente);
        return clienteDto;
    }


    public ClienteDTO salvar(ClienteDTO clienteDto){
        Cliente cliente = clienteMapper.toEntity(clienteDto);
        cliente = clienteRepositorio.save(cliente);
        return clienteMapper.toDto(cliente);
    }


    public void deletar(Integer id) {

        clienteRepositorio.deleteById(id);
    }

}
