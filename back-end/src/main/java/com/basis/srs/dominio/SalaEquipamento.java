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
@Table(name = "sala_equipamento")
@Getter
@Setter
public class SalaEquipamento implements Serializable {

    @EmbeddedId
    private SalaEquipamentoKey id;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @MapsId("idSala")
    @JoinColumn(name="id_sala")
    private Sala sala;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @MapsId("idEquipamento")
    @JoinColumn(name="id_equipamento")
    private Equipamento equipamento;

    @Column(name="quantidade")
    private Integer quantidade;
}