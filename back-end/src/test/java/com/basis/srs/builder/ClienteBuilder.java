package com.basis.srs.builder;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.servico.ClienteServico;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.servico.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Component
public class ClienteBuilder extends ConstrutorDeEntidade<Cliente>{

    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public Cliente construirEntidade() throws ParseException {

        Cliente cliente = new Cliente();
        cliente.setCpf("12345678956");
        cliente.setDataNascimento(LocalDate.now());
        cliente.setEmail("basis@gmail.com");
        cliente.setNome("Felipe");
        cliente.setRg("1234546");
        cliente.setEndereco("Avenida Rua bairro");

        return cliente;
    }

    @Override
    public Cliente persistir(Cliente cliente) {
        ClienteDTO clienteDto = clienteMapper.toDto(cliente);
        ClienteDTO dto = clienteServico.salvar(clienteDto);
        return clienteMapper.toEntity(dto);
    }

    @Override
    public Collection<Cliente> obterTodos() {
        List<ClienteDTO> clientesDTO = clienteServico.listar();
        return clienteMapper.toEntity(clientesDTO) ;
    }

    @Override
    public Cliente obterPorId(Integer id) {
        ClienteDTO cliente = clienteServico.buscarPorId(id);
        return clienteMapper.toEntity(cliente);
    }


    public void deletarPorId(Integer id){
        clienteServico.deletar(id);
    }

    public void limparDados(){
        List<ClienteDTO> clientesDTO = clienteServico.listar();
        clientesDTO.forEach(clienteDTO -> clienteServico.deletar(clienteDTO.getId()));
    }

    public ClienteDTO converterToDto(Cliente cliente){
        return clienteMapper.toDto(cliente);
    }
}
