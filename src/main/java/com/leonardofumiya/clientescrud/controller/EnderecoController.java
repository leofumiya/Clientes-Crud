package com.leonardofumiya.clientescrud.controller;

import com.leonardofumiya.clientescrud.dto.EnderecoDTO;
import com.leonardofumiya.clientescrud.entities.Endereco;
import com.leonardofumiya.clientescrud.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/{idCliente}/cadastro")
    public ResponseEntity<EnderecoDTO> cadastrarEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO, @PathVariable Long idCliente) {
        EnderecoDTO enderecoCadastrado = enderecoService.cadastrarEndereco(enderecoDTO, idCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos() {
        List<EnderecoDTO> enderecos = enderecoService.listarEnderecos();
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorId(@PathVariable Long idEndereco) {
        Endereco endereco = enderecoService.buscarEnderecoPorId(idEndereco);
        EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
        return ResponseEntity.ok(enderecoDTO);
    }

    @PutMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> atualizarEndereco(@PathVariable Long idEndereco, @RequestBody @Valid EnderecoDTO enderecoDTO) {
        EnderecoDTO enderecoAtualizado = enderecoService.atualizarEndereco(idEndereco, enderecoDTO);
        return ResponseEntity.ok(enderecoAtualizado);
    }

    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<EnderecoDTO> excluirEndereco(@PathVariable Long idEndereco) {
        enderecoService.excluirEndereco(idEndereco);
        return ResponseEntity.noContent().build();
    }

}
