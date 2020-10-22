package com.basis.srs.servico;


import com.basis.srs.dominio.Cliente;
import com.basis.srs.repositorio.ClienteRepositorio;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.servico.exception.RegraNegocioException;
import com.basis.srs.servico.mapper.ClienteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServico {

    private final ClienteRepositorio clienteRepositorio;

    private final ClienteMapper clienteMapper;

    public List<ClienteDTO> listar() {
        List<Cliente> clientes = clienteRepositorio.findAll();
        List<ClienteDTO> clienteDto =  clienteMapper.toDto(clientes);
        return clienteDto;
    }

    public ClienteDTO buscarPorId(Integer id) {
        Cliente cliente = clienteRepositorio.findById(id).orElseThrow(()->new RegraNegocioException(
                "Cliente não existe"
        ));
        ClienteDTO clienteDto = clienteMapper.toDto(cliente);
        return clienteDto;
    }


    public ClienteDTO salvar(ClienteDTO clienteDto){
        if(clienteRepositorio.existsByCpf(clienteDto.getCpf()) && clienteDto.getId() == null ){
            throw new RegraNegocioException("Cliente ja cadastrado");
        }
        Cliente cliente = clienteMapper.toEntity(clienteDto);
        cliente = clienteRepositorio.save(cliente);
        return clienteMapper.toDto(cliente);
    }


    public void deletar(Integer id) {
        clienteRepositorio.deleteById(id);
    }

}
