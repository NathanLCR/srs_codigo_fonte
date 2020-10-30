package com.basis.srs.servico;

import com.basis.srs.dominio.Equipamento;
import com.basis.srs.dominio.ReservaEquipamento;
import com.basis.srs.dominio.Sala;
import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.repositorio.EquipamentoRepositorio;
import com.basis.srs.repositorio.ReservaEquipamentoRepositorio;
import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.repositorio.SalaRepositorio;
import com.basis.srs.servico.dto.ReservaDTO;
import com.basis.srs.servico.dto.ReservaEquipamentoDTO;
import com.basis.srs.servico.exception.RegraNegocioException;
import com.basis.srs.servico.mapper.ReservaMapper;
import com.basis.srs.servico.mapper.SalaMapper;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.basis.srs.dominio.Reserva;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional //autor = "lucas.costa"
public class ReservaServico {

    private final ReservaMapper reservaMapper;
    private final ReservaRepositorio reservaRepositorio;
    private final SalaRepositorio salaRepositorio;
    private final ReservaEquipamentoRepositorio reservaEquipamentoRepositorio;
    private final SalaServico salaServico;
    private final SalaMapper salaMapper;
    private final EquipamentoRepositorio equipamentoRepositorio;


    //Get
    public List<ReservaDTO> listar(){
        List<Reserva> listaReserva = reservaRepositorio.findAll();
        List<ReservaDTO> listaDto = reservaMapper.toDto(listaReserva);
        return listaDto;
    }

    //Get por Id
    public ReservaDTO procurarPorId(Integer id) {
        Reserva saida = reservaRepositorio.findById(id).orElse(null);
        ReservaDTO saidaDto = reservaMapper.toDto(saida);
        return saidaDto;
    }

    //Delete
    public void deletar(Integer id){
        Reserva reserva = reservaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException("Essa reserva não existe ainda."));

        reservaEquipamentoRepositorio.deleteAllByReservaId(id);

        reservaRepositorio.deleteById(id);
    }

    //Post e Put
    public ReservaDTO salvar(ReservaDTO reservaDto){
        Sala sala = salaRepositorio.findById(reservaDto.getIdSala()).orElseThrow(() -> new RegraNegocioException("Essa sala não existe."));
        if(reservaDto.getId() == null && verificarSeDataJaEstaReservada(reservaDto)) {
            throw new RegraNegocioException("Data ja esta reservada");
        }
        if (!reservaDto.getDataInicio().isBefore(reservaDto.getDataFim()) && !reservaDto.getDataInicio().equals(reservaDto.getDataFim())) {
            throw new RegraNegocioException("Não é possível cadastrar uma reserva que tenha a data de início após a data do fim.");
        }

        reservaDto.setTotal(calculaTotalReserva(reservaDto));

        Reserva reserva = reservaMapper.toEntity(reservaDto);

        List<ReservaEquipamento> equipamentos = reserva.getEquipamentos();
        reserva.setEquipamentos(new ArrayList<>());
        reservaRepositorio.save(reserva);

        if (reserva.getId() != null){
            reservaEquipamentoRepositorio.deleteAllByReservaId(reserva.getId());
        }

        if (equipamentos != null) {
            equipamentos.forEach(equipamento -> {
                equipamento.setReserva(reserva);
                equipamento.getId().setIdReserva(reserva.getId());
            });
            reservaEquipamentoRepositorio.saveAll(equipamentos);
        }


        reserva.setEquipamentos(equipamentos);
        reserva.setTotal(calculaTotalReserva(reservaDto));
       
        return reservaMapper.toDto(reserva);
    }

    private boolean verificarSeDataJaEstaReservada(ReservaDTO reservaDto){
        List<Reserva> reservas = reservaRepositorio.getAllBySalaId(reservaDto.getIdSala());

        return reservas.stream().anyMatch(reserva -> !(reservaDto.getDataInicio().isAfter(reserva.getDataFim()) | reservaDto.getDataFim().isBefore(reserva.getDataInicio())));
    }

    public Double calculaTotalReserva (ReservaDTO reservaDTO) {
        /*Não tratei todos os erros aqui porque, se chegar-mos ao momentos de calcular o preço, é porque a reserva já foi
        validada pelo método salvar()*/

        Sala sala = salaRepositorio.findById(reservaDTO.getIdSala()).get();
        List<SalaEquipamento> salaEquipamento = sala.getEquipamentos();
        List<ReservaEquipamentoDTO> reservaEquipamentos = reservaDTO.getEquipamentos();

        Double custo = sala.getPrecoDiaria();

        Long dias = ChronoUnit.DAYS.between(reservaDTO.getDataInicio(), reservaDTO.getDataFim()) + 1;

        if (salaEquipamento != null) {
            for (int i = 0; i < salaEquipamento.size(); i++) {
                Equipamento equipamento = equipamentoRepositorio.findById(salaEquipamento.get(i).getEquipamento().getId()).get();
                custo += (equipamento.getPrecoDiaria() * salaEquipamento.get(i).getQuantidade());
            }
        }

        if (reservaEquipamentos != null) {
            for (int i = 0; i < reservaEquipamentos.size(); i++) {
                Equipamento equipamento = equipamentoRepositorio.findById(reservaEquipamentos.get(i).getIdEquipamento()).get();
                custo += (equipamento.getPrecoDiaria() * reservaEquipamentos.get(i).getQuantidade());
            }
        }
        custo *= dias;
        return custo;
    }
}
