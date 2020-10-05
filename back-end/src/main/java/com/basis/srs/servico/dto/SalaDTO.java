package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaDTO {

    private Integer id;

    private Integer idTipoSala;

    private String descricao;

    private Integer limitePessoas;

    private double precoDiaria;

}
