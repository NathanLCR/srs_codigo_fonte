package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

<<<<<<< Updated upstream
=======
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
>>>>>>> Stashed changes
import java.time.LocalDateTime;

@Getter
@Setter//autor = "lucas.costa"
public class ReservaDTO {

    private Integer id;

<<<<<<< Updated upstream
    private ClienteDTO cliente;

    private SalaDTO sala;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private double total;
=======
    @NotNull
    private Integer idCliente;

    @NotNull
    private Integer idSala;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    @NotNull
    @Positive
    private Double total;
>>>>>>> Stashed changes

}
