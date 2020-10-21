package com.basis.srs.web;


import com.basis.srs.servico.ClienteServico;
import com.basis.srs.servico.dto.ClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteRecurso {

    private final ClienteServico clienteServico;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar(){
        List<ClienteDTO> clientes = clienteServico.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(clienteServico.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@Valid @RequestBody ClienteDTO clienteDto) throws URISyntaxException {
        ClienteDTO clienteSalvo = clienteServico.salvar(clienteDto);
        return ResponseEntity.created(new URI("/api/clientes/")).body(clienteSalvo);
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> atualizarCliente(@Valid @RequestBody ClienteDTO clienteDto) throws URISyntaxException {
        ClienteDTO clienteSalvo = clienteServico.salvar(clienteDto);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> remover(@PathVariable("id") Integer id) {
        clienteServico.deletar(id);
        return ResponseEntity.ok().build();
    }

}

