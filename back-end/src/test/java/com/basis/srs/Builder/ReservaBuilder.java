package com.basis.srs.Builder;

import com.basis.srs.dominio.*;
import com.basis.srs.servico.ReservaServico;
import com.basis.srs.servico.dto.ReservaDTO;
import com.basis.srs.servico.mapper.ReservaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;

@Component
public class ReservaBuilder extends ConstrutorDeEntidade<Reserva>{

    @Autowired
    private ReservaServico reservaServico;

    @Autowired
    private ReservaMapper reservaMapper;

    @Override
    public Reserva construirEntidade() throws ParseException {

        Reserva reserva = new Reserva();
        reserva.setDataFim(LocalDate.now());
        reserva.setDataInicio(LocalDate.now());
        reserva.setTotal(450.00);

        return reserva;
    }

    public ReservaDTO converterToDto(Reserva reserva){
        return reservaMapper.toDto(reserva);
    }

    @Override
    protected Reserva persistir(Reserva entidade){
        ReservaDTO dto = reservaMapper.toDto(entidade);
        return  reservaMapper.toEntity(reservaServico.salvar(dto));
    }

    @Override
    protected Collection<Reserva> obterTodos(){
        return null;
    }

    protected  Reserva obterPorId(Long id){
        return null;
    }

}