package com.basis.srs.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "tipo_equipamento")
public class TipoEquipamento {

    @Id
    private Integer id;

    @Column
    private String descricao;

}
