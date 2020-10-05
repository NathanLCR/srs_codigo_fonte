package com.basis.srs.servico;

import com.basis.srs.dominio.Equipamento;
import com.basis.srs.repositorio.EquipamentoRepositorio;
import com.basis.srs.servico.dto.EquipamentoDTO;
import com.basis.srs.servico.mapper.EquipamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipamentoServico {

    private final EquipamentoRepositorio equipamentoRepositorio;

    private final EquipamentoMapper equipamentoMapper;

    public List<EquipamentoDTO> listar(){
        List<Equipamento> equipamentos = equipamentoRepositorio.findAll();

        List<EquipamentoDTO> equipamentosDto = equipamentoMapper.toDto(equipamentos);

        return equipamentosDto;
    };

    public EquipamentoDTO buscarPorId(Integer id){
        Equipamento equipamento = equipamentoRepositorio.findById(id).orElse(null);

        return equipamentoMapper.toDto(equipamento);
    };

    public EquipamentoDTO salvar(EquipamentoDTO equipamentoDto){
        Equipamento equipamento = equipamentoMapper.toEntity(equipamentoDto);

        equipamentoRepositorio.save(equipamento);

        return equipamentoMapper.toDto(equipamento);
    };


    public void deletar(Integer id){
        equipamentoRepositorio.deleteById(id);
    };


}
