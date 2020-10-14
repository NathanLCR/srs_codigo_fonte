package com.basis.srs.servico;

import com.basis.srs.dominio.Sala;
<<<<<<< Updated upstream
=======
import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.repositorio.EquipamentoRepositorio;
import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.repositorio.SalaEquipamentoRepositorio;
>>>>>>> Stashed changes
import com.basis.srs.repositorio.SalaRepositorio;
import com.basis.srs.servico.dto.SalaDto;
import com.basis.srs.servico.mapper.SalaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< Updated upstream

=======
import java.util.ArrayList;
>>>>>>> Stashed changes
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalaServico {

    private final SalaMapper salaMapper;
    private final SalaRepositorio salaRepositorio;
<<<<<<< Updated upstream
=======
    private final SalaEquipamentoRepositorio salaEquipamentoRepositorio;
    private final EquipamentoRepositorio equipamentoRepositorio;
    private final SalaEquipamentoMapper salaEquipamentoMapper;
    private final EquipamentoMapper equipamentoMapper;
    private final ReservaRepositorio reservaRepositorio;
    //private final SalaEquipamentoMapper salaEquipamentoMapper;
>>>>>>> Stashed changes

    //GET
    public List<SalaDto> listarTodas() {
        List<Sala> salas = salaRepositorio.findAll();
        List<SalaDto> salasDto = salaMapper.toDto(salas);
        return salasDto;
    }

    //GET POR ID
    public SalaDto pegarSalaPorId(Integer id) {
        Sala sala = salaRepositorio.findById(id).orElse(null);
        SalaDto salaDto = salaMapper.toDto(sala);
        return salaDto;
    }

    //POST
    public void cadastrarSala(SalaDto salaDto) {
        Sala sala = salaMapper.toEntity(salaDto);
        salaRepositorio.save(sala);

<<<<<<< Updated upstream
    }

    //PUT
    public void alterarSala(SalaDto salaDto) {
        Sala sala = salaMapper.toEntity(salaDto);
        salaRepositorio.save(sala);
    }

=======
        salaEquipamentoRepositorio.saveAll(equipamentos);
        sala.setEquipamentos(equipamentos);
        return salaMapper.toDto(sala);
    }
>>>>>>> Stashed changes
    //DELETE POR ID
    public void deletarSala(Integer id){
        salaRepositorio.deleteById(id);
    }
}
