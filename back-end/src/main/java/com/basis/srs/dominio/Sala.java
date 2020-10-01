package com.basis.srs.dominio;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sala")
public class Sala {

    @Id//Pk
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
    private Double precoDiaria;
}
