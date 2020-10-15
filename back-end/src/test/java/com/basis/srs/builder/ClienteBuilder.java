package com.basis.srs.builder;

import com.basis.srs.dominio.Cliente;
<<<<<<< HEAD
import com.basis.srs.servico.ClienteServico;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.servico.mapper.ClienteMapper;
=======
import com.basis.srs.dominio.Equipamento;
import com.basis.srs.dominio.Sala;
import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.dominio.TipoSala;
import com.basis.srs.servico.ClienteServico;
import com.basis.srs.servico.SalaServico;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.servico.dto.SalaDTO;
import com.basis.srs.servico.mapper.ClienteMapper;
import com.basis.srs.servico.mapper.SalaMapper;
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
<<<<<<< HEAD
import java.util.List;

@Component
public class ClienteBuilder extends ConstrutorDeEntidade<Cliente>{
=======
import java.util.Collections;

@Component
public class ClienteBuilder extends ConstrutorDeEntidade<Cliente> {
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1

    @Autowired
    private ClienteServico clienteServico;

    @Autowired
    private ClienteMapper clienteMapper;

    @Override
<<<<<<< HEAD
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
=======
    public Cliente construirEntidade() {

        Cliente cliente = new Cliente();
        cliente.setCpf("12345678912");
        cliente.setDataNascimento(LocalDate.of(1999,9,2));
        cliente.setEmail("teste@gmail.com");
        cliente.setEndereco("Rua teste");
        cliente.setNome("Teste");
        cliente.setRg("1234567");

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
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
    }
}
