package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class SalaDTO {

    private Integer id;

    @NotNull @Size(max = 255)
    private String descricao;

    @NotNull
    private Integer capacidade;

    @NotNull
    private Integer disponivel;

    @NotNull
    private Integer idTipoSala;

    @NotNull
    private Double precoDiaria;

    @NotNull
    private List<SalaEquipamentoDTO> equipamentos;
}
