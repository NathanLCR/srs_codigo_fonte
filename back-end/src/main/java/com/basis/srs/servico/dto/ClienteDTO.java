package com.basis.srs.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Integer id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private String endereco;

    private String rg;

    private String email;

}
