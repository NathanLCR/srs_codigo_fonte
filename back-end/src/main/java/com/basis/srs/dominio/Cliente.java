package com.basis.srs.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "cliente")
public class   Cliente implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cliente")
   @SequenceGenerator(name = "sq_cliente", allocationSize = 1, sequenceName = "sq_cliente")
   @Column(name = "id")
   private Integer id;

   @Column(name = "nome")
   private String nome;

   @Column(name = "cpf")
   private String cpf;

   @Column(name = "data_nascimento")
   private LocalDate dataNascimento;

   @Column(name = "endereco")
   private String endereco;

   @Column(name = "rg")
   private String rg;

   @Column(name = "email")
   private String email;

   @Column(name="telefone")
   private String telefone;
}
