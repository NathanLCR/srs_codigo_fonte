package com.basis.srs.dominio;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity @Table @Getter @Setter
public class Reserva {


    @Id //Pk
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="id_cliente")
    private Integer id_cliente;

    @Column(name="id_sala")
    private Integer id_sala;

    @Column(name="data_inicio")
    private Date data_inicio;

    @Column(name="data_fim")
    private Date data_fim;

    @Column(name="total")
    private double total;

}
