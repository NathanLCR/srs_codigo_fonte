package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import javax.validation.constraints.NotNull;
=======
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class SalaDTO {

    private Integer id;

<<<<<<< HEAD
    @NotNull @Size(max = 255)
    private String descricao;

    @NotNull
    private Integer capacidade;

    @NotNull
=======
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
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
    private Integer disponivel;

    @NotNull
    private Integer idTipoSala;

    @NotNull
<<<<<<< HEAD
    private Double precoDiaria;

    @NotNull
=======
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
    private List<SalaEquipamentoDTO> equipamentos;
}
