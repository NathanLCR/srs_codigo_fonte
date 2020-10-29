package com.basis.srs.servico;

import com.basis.srs.dominio.Equipamento;
import com.basis.srs.repositorio.EquipamentoRepositorio;
import com.basis.srs.repositorio.ReservaEquipamentoRepositorio;
import com.basis.srs.repositorio.SalaEquipamentoRepositorio;
import com.basis.srs.servico.dto.EquipamentoDTO;
import com.basis.srs.servico.exception.RegraNegocioException;
import com.basis.srs.servico.mapper.EquipamentoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipamentoServico {

    private final EquipamentoRepositorio equipamentoRepositorio;

    private final SalaEquipamentoRepositorio salaEquipamentoRepositorio;

    private final ReservaEquipamentoRepositorio reservaEquipamentoRepositorio;

    private final EquipamentoMapper equipamentoMapper;

    public List<EquipamentoDTO> listar(){
        List<Equipamento> equipamentos = equipamentoRepositorio.findAll();

        List<EquipamentoDTO> equipamentosDto = equipamentoMapper.toDto(equipamentos);

        return equipamentosDto;
    };

    public EquipamentoDTO buscarPorId(Integer id){
        Equipamento equipamento = equipamentoRepositorio.findById(id).orElseThrow(()->new RegraNegocioException("Equipamento não ecnotrnado"));

        return equipamentoMapper.toDto(equipamento);
    };

    public EquipamentoDTO salvar(EquipamentoDTO equipamentoDto){
        Equipamento equipamento = equipamentoMapper.toEntity(equipamentoDto);

        equipamentoRepositorio.save(equipamento);

        return equipamentoMapper.toDto(equipamento);
    };


    public void deletar(Integer id){

        if(salaEquipamentoRepositorio.existsByEquipamentoId(id)){
            throw new RegraNegocioException("Equipamento esta cadastrado em uma sala");
        }

        if (reservaEquipamentoRepositorio.existsByEquipamentoId(id)){
           throw new RegraNegocioException("Equipamento esta cadastrado em uma reserva");
        }

        equipamentoRepositorio.deleteById(id);
    };


}
