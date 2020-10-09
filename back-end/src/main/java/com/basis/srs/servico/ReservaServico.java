package com.basis.srs.servico;

import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.servico.dto.ReservaDTO;
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
        reservaRepositorio.deleteById(id);
    }

    //Post e Put
    public ReservaDTO salvar(ReservaDTO reservaDto){
        if(reservaDto.getId() == null && verificarSeDataJaEstaReservada(reservaDto)){
            throw new RegraNegocioException("Data ja esta reservada");
        }

        Reserva reserva = reservaMapper.toEntity(reservaDto);
        return reservaMapper.toDto(reservaRepositorio.save(reserva));
    }

    private boolean verificarSeDataJaEstaReservada(ReservaDTO reservaDto){
        List<Reserva> reservas = reservaRepositorio.getAllBySalaId(reservaDto.getIdSala());

        return reservas.stream().anyMatch(reserva -> !(reservaDto.getDataInicio().isAfter(reserva.getDataFim()) | reservaDto.getDataFim().isBefore(reserva.getDataInicio())));
    }

}
