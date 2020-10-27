package com.basis.srs.builder;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.servico.ClienteServico;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.servico.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;

@Component
public class ClienteBuilder extends ConstrutorDeEntidade<Cliente> {

    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public Cliente construirEntidade() {

        Cliente cliente = new Cliente();
        cliente.setCpf("12345678912");
        cliente.setDataNascimento(LocalDate.of(1999,9,2));
        cliente.setEmail("teste@gmail.com");
        cliente.setEndereco("Rua teste");
        cliente.setNome("Teste");
        cliente.setRg("1234567");
        cliente.setTelefone("14253698714");
        return cliente;
    }

    @Override
    public Cliente persistir(Cliente entidade) {
        ClienteDTO clienteDto = clienteMapper.toDto(entidade);
        return clienteMapper.toEntity(clienteServico.salvar(clienteDto));
    }

    @Override
    protected Collection<Cliente> obterTodos() {
        return null;
    }

    @Override
    protected Cliente obterPorId(Long id) {
        return null;
    }

    public ClienteDTO converterParaDto (Cliente entidade) {
        return clienteMapper.toDto(entidade);
    }
}