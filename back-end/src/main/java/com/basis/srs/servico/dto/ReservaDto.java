package com.basis.srs.servico.dto;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.dominio.Sala;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter//autor = "lucas.costa"
public class ReservaDto {

    private Integer id;

    private Cliente cliente;

    private Sala sala;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private double total;

}
