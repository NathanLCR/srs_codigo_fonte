package com.basis.srs.servico;

import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.servico.dto.ReservaDto;
import com.basis.srs.servico.mapper.ReservaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.basis.srs.dominio.Reserva;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservaServico {

    private final ReservaMapper reservaMapper;
    private final ReservaRepositorio reservaRepositorio;

    //Get
    public List<ReservaDto> listar(){
        List<Reserva> listaReserva = reservaRepositorio.findAll();
        List<ReservaDto> listaDto = reservaMapper.toDto(listaReserva);
        return listaDto;
    }

    //Get por Id
    public ReservaDto listarPorId(Integer id) {
        Reserva saida = reservaRepositorio.findById(id).orElse(null);
        ReservaDto saidaDto = reservaMapper.toDto(saida);
        return saidaDto;
    }

    //Delete
    public void deletar(Integer id){
        reservaRepositorio.deleteById(id);
    }

    //Post e Put
    public ReservaDto salvar(ReservaDto reservaDto){
        Reserva reserva = reservaMapper.toEntity(reservaDto);
        reservaRepositorio.save(reserva);
        return reservaDto;
    }

}
