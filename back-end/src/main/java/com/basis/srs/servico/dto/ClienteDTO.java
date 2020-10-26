package com.basis.srs.servico.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
public class
ClienteDTO {

    private Integer id;

    @NotNull
    @NotBlank
    @Size(max = 120)
    private String nome;

    @NotNull
    @Size(min = 11,max = 11)
    private String cpf;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @NotNull
    @NotBlank
    private String endereco;

    @NotNull
    @Size(max=12, min=11)
    private String telefone;

    @NotNull
    @Size(max = 7, min = 7)
    private String rg;

    @Email
    @NotNull
    @NotBlank
    @Size(max = 255)
    private String email;

}
