package com.basis.srs.servico;

import com.basis.srs.dominio.Sala;
import com.basis.srs.repositorio.SalaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class SalaService {

    @Autowired
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
