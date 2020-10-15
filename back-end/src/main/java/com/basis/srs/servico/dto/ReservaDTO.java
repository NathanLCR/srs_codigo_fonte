package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter//autor = "lucas.costa"
=======
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
public class ReservaDTO {

    private Integer id;

    @NotNull
    private Integer idCliente;

    @NotNull
    private Integer idSala;

<<<<<<< HEAD
    @NotNull @Size(max = 8, min = 8)
    private LocalDate dataInicio;

    @NotNull @Size(max = 8, min = 8)
    private LocalDate dataFim;

    private Double total;

=======
    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    @NotNull
    @Positive
    private Double total;
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
}
