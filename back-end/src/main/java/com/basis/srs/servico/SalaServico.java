package com.basis.srs.servico;

import com.basis.srs.dominio.Sala;
import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.repositorio.SalaEquipamentoRepositorio;
import com.basis.srs.repositorio.SalaRepositorio;
import com.basis.srs.servico.dto.SalaDTO;
import com.basis.srs.servico.mapper.SalaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalaServico {

    private final SalaMapper salaMapper;
    private final SalaRepositorio salaRepositorio;
    private final SalaEquipamentoRepositorio salaEquipamentoRepositorio;

    //GET
    public List<SalaDTO> listarTodas() {
        List<Sala> salas = salaRepositorio.findAll();
        List<SalaDTO> salasDto = salaMapper.toDto(salas);
        return salasDto;
    }

    //GET POR ID
    public SalaDTO pegarSalaPorId(Integer id) {
        Sala sala = salaRepositorio.findById(id).orElse(null);
        SalaDTO salaDto = salaMapper.toDto(sala);
        return salaDto;
    }

    //POST e put
    public SalaDTO salvar(SalaDTO salaDto) {
        Sala sala = salaMapper.toEntity(salaDto);
        List<SalaEquipamento> equipamentos = sala.getEquipamentos();
        sala.setEquipamentos(new ArrayList<>());
        salaRepositorio.save(sala);
        equipamentos.forEach(equipamento -> {
            equipamento.setSala(sala);
            equipamento.getId().setIdSala(sala.getId());
        });

        salaEquipamentoRepositorio.saveAll(equipamentos);
        sala.setEquipamentos(equipamentos);
        return salaMapper.toDto(sala);

    }

    //DELETE POR ID
    public void deletarSala(Integer id){
        salaRepositorio.deleteById(id);
    }
}
