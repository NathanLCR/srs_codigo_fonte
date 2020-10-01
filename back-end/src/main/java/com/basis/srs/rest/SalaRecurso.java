package com.basis.srs.rest;


import com.basis.srs.dominio.Sala;
import com.basis.srs.servico.SalaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaRecurso {

    private SalaServico salaService;

    @PostMapping
    public ResponseEntity<Sala> cadastrarSala(@RequestBody Sala sala) {
    }

    @PutMapping
    public ResponseEntity<Sala> alterarSala(@RequestBody Sala sala){
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> pegarSalaPorId(@PathVariable Integer id) {
    }

    @GetMapping
    public ResponseEntity<List<Sala>> listarTodas() {
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sala> deletarSala(@PathVariable Integer id) {
    }
}
