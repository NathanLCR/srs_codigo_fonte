package com.basis.srs.servico.dto;

import com.basis.srs.dominio.Equipamento;
import com.basis.srs.dominio.Sala;
import com.basis.srs.dominio.SalaEquipamentoKey;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.List;

public class SalaEquipamentoDTO {

    private List<Integer> equipamentos;

    private Sala sala;

    private Equipamento equipamento;

    private Integer quantidade;
}
