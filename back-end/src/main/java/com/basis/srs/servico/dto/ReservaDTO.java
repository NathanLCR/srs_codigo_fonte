package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter//autor = "lucas.costa"
public class ReservaDTO {

    private Integer id;

    private ClienteDTO cliente;

    private SalaDTO sala;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private double total;

}
