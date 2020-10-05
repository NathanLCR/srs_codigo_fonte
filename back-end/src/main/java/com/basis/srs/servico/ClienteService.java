package com.basis.srs.servico;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.repositorio.ClienteRepositorio;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.servico.mapper.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
<<<<<<< Updated upstream:back-end/src/main/java/com/basis/srs/servico/ClienteService.java
public class ClienteService {
=======
public class ClienteServico {

    private ClienteRepositorio clienteRepositorio;

    private ClienteMapper clienteMapper;

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
        clienteRepositorio.save(cliente);
        return clienteDto;
    }


    public void deletar(Integer id) {
        clienteRepositorio.deleteById(id);
    }


>>>>>>> Stashed changes:back-end/src/main/java/com/basis/srs/servico/ClienteServico.java
}
