package com.basis.srs.servico;

import com.basis.srs.dominio.Equipamento;
import com.basis.srs.dominio.Sala;
import com.basis.srs.repositorio.EquipamentoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipamentoServico {

    @Autowired
    private EquipamentoRepositorio equipamentoRepositorio;

    public void cadastrarEquipamento(Sala s){
    }

    //Put
    public void alterarEquipamento(Equipamento equipamento) {
    }

    //Get
    public List<Equipamento> pegarEquipamentoPorId() {
    }

    //Get por ID
    public Sala listarTodas(Integer id) {
    }

    //Delete por ID
    public void deletarSala(Integer id) {
    }

}
