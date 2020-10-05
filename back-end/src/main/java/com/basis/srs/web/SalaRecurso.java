package com.basis.srs.web;

import com.basis.srs.servico.SalaServico;
import com.basis.srs.servico.dto.SalaDto;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaRecurso {

    private final SalaServico salaService;

    @PostMapping
    public ResponseEntity<SalaDto> cadastrarSala(@RequestBody SalaDto sala) throws URISyntaxException {
        salaService.cadastrarSala(sala);
        return ResponseEntity.created(new URI("/api/salas/")).body(sala);
    }

    @PutMapping
    public ResponseEntity<SalaDto> alterarSala(@RequestBody SalaDto sala){
        salaService.alterarSala(sala);
        return ResponseEntity.ok(sala);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDto> pegarSalaPorId(@PathVariable Integer id) {
        SalaDto sala = salaService.pegarSalaPorId(id);
        return ResponseEntity.ok(sala);
    }

    @GetMapping
    public ResponseEntity<List<SalaDto>> listarTodas() {
        List<SalaDto> salaDtos = salaService.listarTodas();
        return ResponseEntity.ok(salaDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SalaDto> deletarSala(@PathVariable Integer id) {
        SalaDto salaDto = salaService.pegarSalaPorId(id);
        salaService.deletarSala(id);
        return ResponseEntity.ok().build();
    }
}
