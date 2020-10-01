package com.basis.srs.dominio;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class SalaEquipamentoKey implements Serializable {

    @Column(name="id_sala")
    private Integer idSala;

    @Column(name="id_equipamento")
    private Integer idEquipamento;
}
