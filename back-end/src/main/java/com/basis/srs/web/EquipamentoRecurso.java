package com.basis.srs.web;


import com.basis.srs.dominio.Equipamento;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
