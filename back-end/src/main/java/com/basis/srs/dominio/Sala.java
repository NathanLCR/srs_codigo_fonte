package com.basis.srs.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
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
    private double precoDiaria;
}
