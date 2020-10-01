package com.basis.srs.rest;

import com.basis.srs.dominio.Sala;
import com.basis.srs.servico.SalaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaRecurso {

    private SalaServico salaService;

    @PostMapping
    public void cadastrarSala(@RequestBody Sala sala) {
    }

    @PutMapping
    public void alterarSala(@RequestBody Sala sala){}

    @GetMapping("/{id}")
    public void pegarSalaPorId(@PathVariable Integer id) {}

    @GetMapping
    public void listarTodas() {}

    @DeleteMapping("/{id}")
    public void deletarSala(@PathVariable Integer id) {}
}
