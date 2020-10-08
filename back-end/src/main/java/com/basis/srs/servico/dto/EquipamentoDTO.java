package com.basis.srs.servico.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EquipamentoDTO {

    private Integer id;

    @NotNull
    @Size(max = 120)
    private String nome;

    @NotNull
    private Integer idTipoEquipamento;

    @NotNull
    @Positive
    private double precoDiaria;

    @NotNull
    @Min(0)
    @Max(1)
    private int obrigatorio;
}
