package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class SalaDTO {

    private Integer id;

    @NotNull
    @Positive
    private Double precoDiaria;

    @NotNull
    @NotBlank
    private String descricao;

    @NotNull
    @Positive
    private Integer capacidade;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer disponivel;

    @NotNull
    private Integer idTipoSala;

    @NotNull
    private List<SalaEquipamentoDTO> equipamentos;
}
