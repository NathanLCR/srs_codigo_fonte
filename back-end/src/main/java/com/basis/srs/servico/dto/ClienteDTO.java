package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
public class ClienteDTO {

    private Integer id;

    @NotNull @Size(max = 120)
    private String nome;

    @NotNull @Size(max = 11, min = 11)
    private String cpf;

    @NotNull @Past
    private LocalDate dataNascimento;

    @NotNull @Size(max = 255)
    private String endereco;

    @NotNull @Size(max = 7, min = 7)
    private String rg;

    @NotNull @Size(max = 255) @Email
    private String email;

}
