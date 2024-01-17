package com.leonardofumiya.clientescrud.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leonardofumiya.clientescrud.entities.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Pattern(regexp = "\\d{10,11}", message = "O CPF deve conter 10 ou 11 dígitos")
    private String cpf;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "O telefone deve conter 11 dígitos")
    private String telefone;
    @NotBlank
    @Email
    private String email;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.dataNascimento = cliente.getDataNascimento();
        this.telefone = cliente.getTelefone();
        this.email = cliente.getEmail();
    }

}
