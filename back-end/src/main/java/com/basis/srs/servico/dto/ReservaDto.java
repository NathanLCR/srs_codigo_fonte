package com.basis.srs.servico.dto;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.dominio.Sala;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
public class ReservaDto {

    private Integer id;

    private Cliente cliente;

    private Sala sala;

    private Date dataInicio;

    private Date dataFim;

    private double total;

}
