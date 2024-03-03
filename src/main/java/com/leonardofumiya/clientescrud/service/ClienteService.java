package com.leonardofumiya.clientescrud.service;

import com.leonardofumiya.clientescrud.dto.ClienteDTO;
import com.leonardofumiya.clientescrud.entities.Cliente;
import com.leonardofumiya.clientescrud.exception.ClienteNaoEncontradoException;
import com.leonardofumiya.clientescrud.exception.EnderecoNaoEncontradoException;
import com.leonardofumiya.clientescrud.repository.ClienteRepository;
import com.leonardofumiya.clientescrud.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
            Cliente cliente = new Cliente();
            cliente.setNome(clienteDTO.getNome());
            cliente.setCpf(clienteDTO.getCpf());
            cliente.setDataNascimento(clienteDTO.getDataNascimento());
            cliente.setTelefone(clienteDTO.getTelefone());
            cliente.setEmail(clienteDTO.getEmail());

            cliente = clienteRepository.save(cliente);
            return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(x -> new ClienteDTO(x)).toList();
    }

    @Transactional
    public ClienteDTO atualizarCliente(Long idCliente, ClienteDTO clienteDTO) {
        try {
            Cliente clienteCadastrado = clienteRepository.getReferenceById(idCliente);
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
            if (clienteDTO.getIdEndereco() != null) {
                clienteCadastrado.setIdEndereco(clienteDTO.getIdEndereco());
            }
            clienteCadastrado = clienteRepository.save(clienteCadastrado);
            return new ClienteDTO(clienteCadastrado);
        }catch (EntityNotFoundException e) {
            throw new ClienteNaoEncontradoException("Pessoa não encontrada com o ID: " + idCliente);
        }
    }

    @Transactional
    public void excluirCliente(Long idCliente) {
        clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + idCliente));
        clienteRepository.deleteById(idCliente);
    }

    @Transactional(readOnly = true)
    public Cliente buscarClientePorId(Long idCliente) {
        log.info("Busca cliente por ID {idCliente}");
        log.info(idCliente);
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + idCliente));
    }

}
