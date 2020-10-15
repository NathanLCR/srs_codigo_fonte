package com.basis.srs.servico.dto;


import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
=======
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1

@Getter
@Setter
public class EquipamentoDTO {

    private Integer id;

<<<<<<< HEAD
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
=======
    @NotNull
    @Size(max = 120)
    @NotBlank
    private String nome;

    @NotNull
    private Integer idTipoEquipamento;

    @NotNull
    @Positive
    private double precoDiaria;

    @NotNull
    @Min(0)
    @Max(1)
    private Integer obrigatorio;
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
}
