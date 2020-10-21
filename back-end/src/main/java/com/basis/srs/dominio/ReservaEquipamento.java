package com.basis.srs.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "reserva_equipamento")
@Getter
@Setter
public class ReservaEquipamento implements Serializable {

    @EmbeddedId
    private ReservaEquipamentoKey id;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @MapsId("idReserva")
    @JoinColumn(name="id_reserva")
    private Reserva reserva;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @MapsId("idEquipamento")
    @JoinColumn(name="id_equipamento")
    private Equipamento equipamento;

    @Column(name="quantidade")
    private Integer quantidade;
}
