package com.leonardofumiya.clientescrud.controller;

import com.leonardofumiya.clientescrud.dto.EnderecoDTO;
import com.leonardofumiya.clientescrud.entities.Endereco;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endercos")
public class EnderecoController {

    @PostMapping
    public ResponseEntity<EnderecoDTO> cadastrar(@RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoCadastrado = service.cadastrar(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listar() {
        List<EnderecoDTO> enderecos = service.listar();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable Long id) {
        Endereco endereco = service.buscarEnderecoPorId(id);
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        return ResponseEntity.ok(enderecoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoAtualizado = service.atualizar(id, enderecoDTO);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoDTO> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
