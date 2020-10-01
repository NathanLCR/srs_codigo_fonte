package com.basis.srs.servico;

import com.basis.srs.dominio.Sala;
import com.basis.srs.repositorio.SalaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaServico {

    private SalaRepositorio sr;

    //Post
    public void cadastrarSala(Sala s){
    }

    //Put
    public void alterarSala(Sala s, Integer id) {
    }

    //Get
    public List<Sala> pegarSalaPorId() {
    }

    //Get por ID
    public Sala listarTodas(Integer id) {
    }

    //Delete por ID
    public void deletarSala(Integer id) {
    }
}
