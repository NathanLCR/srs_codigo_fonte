package com.basis.srs.servico.dto;

import com.basis.srs.dominio.SalaEquipamento;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalaDTO {

    private Integer id;

    private Integer idTipoSala;

    private String descricao;

    private Integer limitePessoas;

    private double precoDiaria;

    private List<SalaEquipamento> salaEquipamentos;

}
