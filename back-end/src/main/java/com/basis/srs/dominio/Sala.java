package com.basis.srs.dominio;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Sala{

    @Id//Pk
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_sala")
    private TipoSala idTipoSala;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "limite_pessoas")
    private Integer limitePessoas;

    @Column(name = "preco_diaria")
    private double precoDiaria;
}
