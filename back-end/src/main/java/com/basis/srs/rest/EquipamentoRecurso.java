package com.basis.srs.rest;


import com.basis.srs.dominio.Equipamento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
@RequiredArgsConstructor
public class EquipamentoRecurso {

    @GetMapping()
    public void listarTodosEquipamentos(){};

    @GetMapping("/{id}")
    public void pegarEquipamentoPorId(@PathVariable Integer id){};

    @PostMapping()
    public void cadastrarEquipamento(Equipamento equipamento){};

    @PutMapping()
    public void alterarEquipamento(){};

    @DeleteMapping("/{id}")
    public void deletarEquipamento(@PathVariable Integer id){};

}
