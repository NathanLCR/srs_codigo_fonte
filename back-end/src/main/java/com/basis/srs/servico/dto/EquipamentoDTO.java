package com.basis.srs.servico.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EquipamentoDTO {

    private Integer id;

    @NotNull
    @Size(max = 120)
    @NotBlank
    private String nome;

    @NotNull
    private Integer idTipoEquipamento;

    @NotNull
    @Positive
    private double precoDiaria;

}
