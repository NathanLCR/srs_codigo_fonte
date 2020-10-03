package com.basis.srs.servico.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EquipamentoDTO implements Serializable {

    private Integer id;

    private String nome;

    private DominioFixoDTO tipoEquipamento;

    private double precoDiaria;

    private int obrigatorio;
}
