package com.leonardofumiya.clientescrud.service;

import com.leonardofumiya.clientescrud.dto.ClienteDTO;
import com.leonardofumiya.clientescrud.entities.Cliente;
import com.leonardofumiya.clientescrud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    public ClienteDTO cadastrar(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEmail(clienteDTO.getEmail());

        cliente = repository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listar() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream().map(x -> new ClienteDTO(x)).toList();
    }

    @Transactional
    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        Cliente clienteCadastrado = repository.getReferenceById(id);
        if (clienteDTO.getNome() != null) {
            clienteCadastrado.setNome(clienteDTO.getNome());
        }
        if (clienteDTO.getCpf() != null) {
            clienteCadastrado.setCpf(clienteDTO.getCpf());
        }
        if (clienteDTO.getDataNascimento() != null) {
            clienteCadastrado.setDataNascimento(clienteDTO.getDataNascimento());
        }
        if (clienteDTO.getTelefone() != null) {
            clienteCadastrado.setTelefone(clienteDTO.getTelefone());
        }
        if (clienteDTO.getEmail() != null) {
            clienteCadastrado.setEmail(clienteDTO.getEmail());
        }
        clienteCadastrado = repository.save(clienteCadastrado);
        return new ClienteDTO(clienteCadastrado);
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Cliente buscarClientePorId(Long id) {
        return repository.getReferenceById(id);
    }

}
