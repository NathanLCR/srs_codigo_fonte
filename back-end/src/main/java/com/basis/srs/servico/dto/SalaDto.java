package com.basis.srs.servico.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class SalaDto {

    private Integer id;

    private Integer idTipoSala;

    private String descricao;

    private Integer limitePessoas;

    private double precoDiaria;
}
