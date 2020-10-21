package com.basis.srs.builder;


import com.basis.srs.dominio.Cliente;
import com.basis.srs.dominio.Equipamento;
import com.basis.srs.dominio.Reserva;
import com.basis.srs.dominio.ReservaEquipamento;
import com.basis.srs.dominio.Sala;
import com.basis.srs.servico.ReservaServico;
import com.basis.srs.servico.dto.ReservaDTO;
import com.basis.srs.servico.mapper.ReservaMapper;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaBuilder extends ConstrutorDeEntidade<Reserva>{

    @Autowired
    private ReservaServico reservaServico;
    @Autowired
    private ReservaMapper reservaMapper;
    @Autowired
    private EquipamentoBuilder equipamentoBuilder;
    @Autowired
    private ClienteBuilder clienteBuilder;
    @Autowired
    private SalaBuilder salaBuilder;
    
    @Override
    public Reserva construirEntidade() throws ParseException {
        Reserva reserva = new Reserva();
        Cliente cliente = clienteBuilder.construir();
        reserva.setCliente(cliente);
        Sala sala = salaBuilder.construir();
        reserva.setSala(sala);
        reserva.setDataFim(LocalDate.of(2020,11,8));
        reserva.setDataInicio(LocalDate.of(2020,11,7));

        Equipamento equipamento = equipamentoBuilder.construir();
        ReservaEquipamento reservaEquipamento = new ReservaEquipamento();
        reservaEquipamento.setEquipamento(equipamento);
        reservaEquipamento.setQuantidade(10);
        reservaEquipamento.setReserva(reserva);
        reserva.setEquipamentos(Collections.singletonList(reservaEquipamento));

        return reserva;
    }

    @Override
    public Reserva persistir(Reserva entidade) {
        ReservaDTO dto = reservaMapper.toDto(entidade);
        return reservaMapper.toEntity(reservaServico.salvar(dto));
    }

    public ReservaDTO converterParaDto(Reserva entity){
        return reservaMapper.toDto(entity);
    }

    @Override
    protected Collection<Reserva> obterTodos() {
        return null;
    }

    @Override
    protected Reserva obterPorId(Long id) {
        return null;
    }
}
