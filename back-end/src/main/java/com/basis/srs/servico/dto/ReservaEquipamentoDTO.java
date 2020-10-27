package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReservaEquipamentoDTO {

    @NotNull
    private Integer idReserva;

    @NotNull
    private Integer idEquipamento;

    @NotNull
    private Integer quantidade;
}
