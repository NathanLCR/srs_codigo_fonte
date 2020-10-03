package com.basis.srs.servico;

import com.basis.srs.dominio.Sala;
import com.basis.srs.repositorio.SalaRepositorio;
import com.basis.srs.servico.dto.SalaDto;
import com.basis.srs.servico.mapper.SalaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalaServico {

    private final SalaMapper salaMapper;
    private final SalaRepositorio salaRepositorio;

    //GET
    public List<SalaDto> listarTodas() {
        List<Sala> salas = salaRepositorio.findAll();
        List<SalaDto> salasDto = salaMapper.toDto(salas);
        return salasDto;
    }

    //GET POR ID
    public SalaDto pegarSalaPorId(Integer id) {
        Sala sala = salaRepositorio.findById(id)
        SalaDto salaDto = salaMapper.toDto(sala);
        return salaDto;
    }

    //POST
    public void cadastrarSala(SalaDto salaDto) {
        Sala sala = salaMapper.toEntity(salaDto);
        salaRepositorio.save(sala);

    }

    //PUT
    public void alterarSala(SalaDto salaDto) {
        Sala sala = salaMapper.toEntity(salaDto);
        salaRepositorio.save(sala);
    }

    //DELETE POR ID
    public void deletarSala(Integer id){
        salaRepositorio.deleteById(id);
    }
}