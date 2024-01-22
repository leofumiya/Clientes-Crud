package com.leonardofumiya.clientescrud.dto;

import com.leonardofumiya.clientescrud.entities.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private Long idEndereco;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    private String numero;
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;

    public EnderecoDTO(Endereco entity) {
        this.idEndereco = entity.getId();
        this.logradouro = entity.getLogradouro();
        this.bairro = entity.getBairro();
        this.numero = entity.getNumero();
        this.cep = entity.getCep();
        this.cidade = entity.getCidade();
        this.uf = entity.getUf();
    }

}
