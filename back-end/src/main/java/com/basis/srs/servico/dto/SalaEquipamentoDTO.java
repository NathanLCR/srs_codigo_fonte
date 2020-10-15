package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
<<<<<<< HEAD
import javax.validation.constraints.Size;
=======
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1

@Getter
@Setter
public class SalaEquipamentoDTO {

    @NotNull
    private Integer idSala;

    @NotNull
    private Integer idEquipamento;

    @NotNull
    private Integer quantidade;
}
