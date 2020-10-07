package com.basis.srs.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ClienteDTO {


    private Integer id;

    @NotNull
    @Size(max = 120)
    @NotBlank
    private String nome;

    @NotNull
    @Size(min = 11,max = 11)
    private String cpf;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @NotNull
    private String endereco;

    @NotNull
    @Size(max = 7, min = 7)
    private String rg;

    @Email
    @NotNull
    @NotBlank
    private String email;

}
