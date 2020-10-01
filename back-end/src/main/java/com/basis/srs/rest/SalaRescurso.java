package com.basis.srs.rest;


import com.basis.srs.dominio.Sala;
import com.basis.srs.servico.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/salas")

public class SalaRescurso {

    @Autowired
    private SalaService ss;

    @PostMapping
    public ResponseEntity<Sala> cadastrarSala(@RequestBody Sala s) {
    }

    @PutMapping
    public ResponseEntity<Sala> alterarSala(@RequestBody Sala s){
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
