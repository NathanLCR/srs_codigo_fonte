package com.basis.srs.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

   @Id
   @GeneratedValue
   private int id;

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
