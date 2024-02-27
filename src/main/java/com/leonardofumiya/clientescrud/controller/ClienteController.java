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
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteCadastrado = clienteService.cadastrarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long idCliente) {
        Cliente cliente = clienteService.buscarClientePorId(idCliente);
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long idCliente, @RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(idCliente, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> excluirCliente(@PathVariable Long idCliente) {
        clienteService.excluirCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

}
