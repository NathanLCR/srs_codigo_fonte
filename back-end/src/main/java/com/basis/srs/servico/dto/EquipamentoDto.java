package com.basis.srs.servico.dto;

import com.basis.srs.dominio.TipoEquipamento;
import lombok.*;

@Getter
@Setter
public class EquipamentoDto {

    private Integer id;

    private String nome;

    private TipoEquipamento tipoEquipamento;

    private double precoDiaria;

    private int obrigatorio;
}
