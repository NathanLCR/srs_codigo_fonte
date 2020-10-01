package com.basis.srs.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data @AllArgsConstructor @NoArgsConstructor @Entity
public class TipoSala {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "descricao")
    private String descricao;

}
