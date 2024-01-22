package com.leonardofumiya.clientescrud.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

}
