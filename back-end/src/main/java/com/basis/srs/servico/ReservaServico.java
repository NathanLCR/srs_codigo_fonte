package com.basis.srs.servico;

import com.basis.srs.dominio.Sala;
import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.repositorio.SalaRepositorio;
import com.basis.srs.servico.dto.ReservaDTO;
import com.basis.srs.servico.dto.SalaDTO;
import com.basis.srs.servico.exception.RegraNegocioException;
import com.basis.srs.servico.mapper.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.basis.srs.dominio.Reserva;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional //autor = "lucas.costa"
public class ReservaServico {

    private final ReservaMapper reservaMapper;
    private final ReservaRepositorio reservaRepositorio;
    private final SalaRepositorio salaRepositorio;


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
        Sala sala = reserva.getSala();
        sala.setDisponivel(1);
        reservaRepositorio.deleteById(id);
    }

    //Post e Put
    public ReservaDTO salvar(ReservaDTO reservaDto){
        Sala sala = salaRepositorio.findById(reservaDto.getIdSala()).orElseThrow(() -> new RegraNegocioException("Essa sala não existe."));
        if(reservaDto.getId() == null && verificarSeDataJaEstaReservada(reservaDto)) {
            throw new RegraNegocioException("Data ja esta reservada");
        }

        if (sala.getDisponivel() == 0) {
            throw new RegraNegocioException("Essa sala já está reservada, por favor escolha outra.");
        }
        else {
            sala.setDisponivel(0);
        }

        Reserva reserva = reservaMapper.toEntity(reservaDto);
        return reservaMapper.toDto(reservaRepositorio.save(reserva));
    }

    private boolean verificarSeDataJaEstaReservada(ReservaDTO reservaDto){
        List<Reserva> reservas = reservaRepositorio.getAllBySalaId(reservaDto.getIdSala());

        return reservas.stream().anyMatch(reserva -> !(reservaDto.getDataInicio().isAfter(reserva.getDataFim()) | reservaDto.getDataFim().isBefore(reserva.getDataInicio())));
    }

}
