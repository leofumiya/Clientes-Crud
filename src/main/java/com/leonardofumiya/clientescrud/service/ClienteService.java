package com.leonardofumiya.clientescrud.service;

import com.leonardofumiya.clientescrud.dto.ClienteDTO;
import com.leonardofumiya.clientescrud.entities.Cliente;
import com.leonardofumiya.clientescrud.exception.ClienteNaoEncontradoException;
import com.leonardofumiya.clientescrud.exception.EnderecoNaoEncontradoException;
import com.leonardofumiya.clientescrud.repository.ClienteRepository;
import com.leonardofumiya.clientescrud.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public ClienteDTO cadastrar(ClienteDTO clienteDTO) {
        if (enderecoRepository.findById(clienteDTO.getEndereco().getIdEndereco()).isPresent()) {
            Cliente cliente = new Cliente();
            cliente.setNome(clienteDTO.getNome());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setDataNascimento(clienteDTO.getDataNascimento());
            cliente.setTelefone(clienteDTO.getTelefone());
            cliente.setEmail(clienteDTO.getEmail());
            cliente.setEndereco(clienteDTO.getEndereco());

            cliente = repository.save(cliente);
            return new ClienteDTO(cliente);
        } else {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + clienteDTO.getEndereco().getIdEndereco());
        }
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listar() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream().map(x -> new ClienteDTO(x)).toList();
    }

    @Transactional
    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        try {
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
            if (clienteDTO.getEndereco().getIdEndereco() != null) {
                enderecoRepository.findById(clienteCadastrado.getEndereco().getIdEndereco())
                        .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado  com o ID: " + clienteDTO.getEndereco().getIdEndereco()));
                clienteCadastrado.setEndereco(clienteDTO.getEndereco());
            }
            clienteCadastrado = repository.save(clienteCadastrado);
            return new ClienteDTO(clienteCadastrado);
        }catch (EntityNotFoundException e) {
            throw new ClienteNaoEncontradoException("Pessoa não encontrada com o ID: " + id);
        }
    }

    @Transactional
    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + id));
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Cliente buscarClientePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + id));
    }

}
