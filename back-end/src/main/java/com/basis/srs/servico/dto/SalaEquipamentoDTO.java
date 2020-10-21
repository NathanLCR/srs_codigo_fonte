package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
<<<<<<< HEAD

=======
>>>>>>> manter-equipamento

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
