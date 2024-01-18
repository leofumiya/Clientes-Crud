package com.leonardofumiya.clientescrud.controller;

import com.leonardofumiya.clientescrud.dto.ClienteDTO;
import com.leonardofumiya.clientescrud.entities.Cliente;
import com.leonardofumiya.clientescrud.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteCadastrado = service.cadastrar(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<ClienteDTO> clientes = service.listar();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id) {
        Cliente cliente = service.buscarClientePorId(id);
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = service.atualizar(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDTO> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
