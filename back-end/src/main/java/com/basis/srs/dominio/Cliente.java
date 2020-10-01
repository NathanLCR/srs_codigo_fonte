package com.basis.srs.dominio;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "cliente")
public class Cliente implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) //sequence aqui
   private Integer id;

   @Column(name = "nome")
   private String nome;

   @Column(name = "cpf")
   private String cpf;

   @Column(name = "data_nascimento")
   private Date data_nascimento;

   @Column(name = "endereco")
   private String endereco;

   @Column(name = "rg")
   private String rg;


}
