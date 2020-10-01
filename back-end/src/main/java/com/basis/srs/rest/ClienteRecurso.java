package com.basis.srs.rest;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRecurso {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id){

    }

    @GetMapping
    public ResponseEntity<List<Cliente>>  listar() {

    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {

    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {

    }

}

