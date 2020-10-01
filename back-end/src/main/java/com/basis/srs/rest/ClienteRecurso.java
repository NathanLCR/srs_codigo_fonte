package com.basis.srs.rest;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.repositorio.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteRecurso {

    @GetMapping("/{id}")
    public void buscarPorId(@PathVariable Integer id){}

    @GetMapping
    public void  listar() {}

    @PostMapping
    public void salvar(@RequestBody Cliente cliente) {}

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {}

}

