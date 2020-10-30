package com.basis.srs.web;


import com.basis.srs.servico.EquipamentoServico;
import com.basis.srs.servico.dto.EquipamentoDTO;
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
@RequestMapping("/api/equipamentos")
@RequiredArgsConstructor
public class EquipamentoRecurso {

    private final EquipamentoServico equipamentoServico;

    @GetMapping()
    public ResponseEntity<List<EquipamentoDTO>> listar(){
        List<EquipamentoDTO> equipamentos = equipamentoServico.listar();
        return ResponseEntity.ok(equipamentos);
    };

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> buscarPorId(@PathVariable Integer id){
        EquipamentoDTO equipamento = equipamentoServico.buscarPorId(id);

        return ResponseEntity.ok(equipamento);
    };

    @PostMapping
    public ResponseEntity<EquipamentoDTO> cadastrarEquipamento(@Valid @RequestBody EquipamentoDTO equipamento) throws URISyntaxException {

        EquipamentoDTO equipamentoSalvo = equipamentoServico.salvar(equipamento);

        return ResponseEntity.created(new URI("/api/equipamentos")).body(equipamentoSalvo);
    };

    @PutMapping
    public ResponseEntity<EquipamentoDTO> alterarEquipamento(@Valid @RequestBody EquipamentoDTO equipamento) throws URISyntaxException {
        EquipamentoDTO equipamentoSalvo = equipamentoServico.salvar(equipamento);

        return ResponseEntity.ok(equipamentoSalvo);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> deletarEquipamento(@PathVariable Integer id){
        equipamentoServico.deletar(id);
        return ResponseEntity.ok().build();
    };

}
