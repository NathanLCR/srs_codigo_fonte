package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Setter
@Getter
public class ClienteDTO {

    @Autowired
    private ClienteDTO clienteDTO;

    private Integer id;

    private String nome;

    private String cpf;

    private LocalDate dataNascimento;

    private String endereco;

    private String rg;

    private String email;
}
