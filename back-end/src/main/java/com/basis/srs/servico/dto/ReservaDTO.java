package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter//autor = "lucas.costa"
public class ReservaDTO {

    private Integer id;

    @NotNull
    private Integer idCliente;

    @NotNull
    private Integer idSala;

    @NotNull @Size(max = 8, min = 8)
    private LocalDate dataInicio;

    @NotNull @Size(max = 8, min = 8)
    private LocalDate dataFim;

    private Double total;

}
