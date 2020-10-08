package com.basis.srs.builder;

import com.basis.srs.dominio.Equipamento;
import com.basis.srs.dominio.TipoEquipamento;
import com.basis.srs.servico.EquipamentoServico;
import com.basis.srs.servico.dto.EquipamentoDTO;
import com.basis.srs.servico.mapper.EquipamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
public class EquipamentoBuilder extends ConstrutorDeEntidade<Equipamento> {

    @Autowired
    private EquipamentoServico equipamentoServico;
    @Autowired
    private EquipamentoMapper equipamentoMapper;

    @Override
    protected Equipamento construirEntidade() throws ParseException {
        Equipamento equipamento = new Equipamento();
        TipoEquipamento tipo = new TipoEquipamento();
        tipo.setId(1);
        equipamento.setNome("Mesa");
        equipamento.setObrigatorio(1);
        equipamento.setPrecoDiaria(10.90);
        equipamento.setTipoEquipamento(tipo);
        return equipamento;
    }

    @Override
    public Equipamento persistir(Equipamento entidade) {
        EquipamentoDTO dto = equipamentoMapper.toDto(entidade);
        return equipamentoMapper.toEntity(equipamentoServico.salvar(dto));
    }

    @Override
    protected List<Equipamento> obterTodos() {
        return null;
    }

    @Override
    protected Equipamento obterPorId(Long id) {
        return null;
    }
}