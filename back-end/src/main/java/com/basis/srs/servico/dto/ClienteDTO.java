package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ClienteDTO {

    private Integer id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private String endereco;

    private String rg;

    private String email;

}
