package com.basis.srs.builder;

import com.basis.srs.dominio.Equipamento;
import com.basis.srs.dominio.Sala;
import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.dominio.TipoSala;
import com.basis.srs.servico.SalaServico;
import com.basis.srs.servico.dto.SalaDTO;
import com.basis.srs.servico.mapper.SalaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;

@Component
public class SalaBuilder extends ConstrutorDeEntidade<Sala> {

    @Autowired
    private SalaServico salaServico;

    @Autowired
    private SalaMapper salaMapper;

    @Autowired
    private EquipamentoBuilder equipamentoBuilder;

    @Override
    public Sala construirEntidade() throws ParseException {

        Equipamento equipamentos = equipamentoBuilder.construir();
        Sala sala = new Sala();
        sala.setCapacidade(50);
        sala.setDescricao("Sala Muito linda!");
        sala.setPrecoDiaria(50.90);
        TipoSala tipoSala = new TipoSala();
        tipoSala.setId(1);

        SalaEquipamento salaEquipamento = new SalaEquipamento();
        salaEquipamento.setSala(sala);
        salaEquipamento.setQuantidade(85);
        salaEquipamento.setEquipamento(equipamentos);

        sala.setEquipamentos(Collections.singletonList(salaEquipamento));
        sala.setTipoSala(tipoSala);

        return sala;
   }

    @Override
    public Sala persistir(Sala entidade) {
        SalaDTO salaDTO = salaMapper.toDto(entidade);
        return salaMapper.toEntity(salaServico.salvar(salaDTO));
    }

    @Override
    protected Collection<Sala> obterTodos() { return null;
    }

    @Override
    protected Sala obterPorId(Long id) {
        return null;
    }

    public SalaDTO converterParaDto (Sala sala) {
        return salaMapper.toDto(sala);
    }
}
