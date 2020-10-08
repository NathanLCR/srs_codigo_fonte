package com.basis.srs.web;

import com.basis.srs.servico.SalaServico;
import com.basis.srs.servico.dto.SalaDTO;
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
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaRecurso {

    private final SalaServico salaService;

    @PostMapping
    public ResponseEntity<SalaDTO> cadastrarSala(@Valid @RequestBody SalaDTO sala) throws URISyntaxException {
        SalaDTO salaCriada = salaService.salvar(sala);
        return ResponseEntity.created(new URI("/api/salas/")).body(salaCriada);
    }

    @PutMapping
    public ResponseEntity<SalaDTO> alterarSala(@Valid @RequestBody SalaDTO sala){
        SalaDTO salaAtualizada = salaService.salvar(sala);
        return ResponseEntity.ok(salaAtualizada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> pegarSalaPorId(@PathVariable Integer id) {
        SalaDTO sala = salaService.pegarSalaPorId(id);
        return ResponseEntity.ok(sala);
    }

    @GetMapping
    public ResponseEntity<List<SalaDTO>> listarTodas() {
        List<SalaDTO> salaDtos = salaService.listarTodas();
        return ResponseEntity.ok(salaDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SalaDTO> deletarSala(@PathVariable Integer id) {
        SalaDTO salaDto = salaService.pegarSalaPorId(id);
        salaService.deletarSala(id);
        return ResponseEntity.ok().build();
    }
}
