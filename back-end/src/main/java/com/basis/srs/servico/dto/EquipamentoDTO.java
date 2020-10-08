package com.basis.srs.servico.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class EquipamentoDTO implements Serializable {

    private Integer id;

    @NotNull @Size(max = 120)
    private String nome;

<<<<<<< Updated upstream
    private DominioFixoDTO tipoEquipamento;
=======
    @NotNull
    private Integer idTipoEquipamento;
>>>>>>> Stashed changes

    @NotNull
    private double precoDiaria;

    @NotNull
    private int obrigatorio;
}
