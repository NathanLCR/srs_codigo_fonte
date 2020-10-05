package com.basis.srs.servico;

import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.servico.dto.ReservaDTO;
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

    private ReservaMapper reservaMapper;
    private ReservaRepositorio reservaRepositorio;


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
        Reserva reserva = reservaMapper.toEntity(reservaDto);
        reservaRepositorio.save(reserva);
        return reservaDto;
    }

}
