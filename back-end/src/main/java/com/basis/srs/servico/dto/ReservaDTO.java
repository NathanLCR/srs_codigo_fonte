package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter//autor = "lucas.costa"
public class ReservaDTO {

    private Integer id;

    private Integer idCliente;

    private Integer idSala;

    private LocalDate dataInicio;

    private LocalDate dataFim;

    private double total;

}
