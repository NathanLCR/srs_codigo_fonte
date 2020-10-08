package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
=======
>>>>>>> 4ed5152ba517f2452b8dacf0649f0fcf308031c8
import java.time.LocalDate;

@Getter
@Setter
public class ReservaDTO {

    private Integer id;

<<<<<<< HEAD
    @NotNull
    private Integer idCliente;

    @NotNull
    private Integer idSala;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;
=======
    private Integer idCliente;

    private Integer idSala;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private Double total;
>>>>>>> 4ed5152ba517f2452b8dacf0649f0fcf308031c8

    @NotNull
    @Positive
    private Double total;
}
