package com.leonardofumiya.clientescrud.service;

import com.leonardofumiya.clientescrud.dto.EnderecoDTO;
import com.leonardofumiya.clientescrud.entities.Endereco;
import com.leonardofumiya.clientescrud.exception.EnderecoNaoEncontradoException;
import com.leonardofumiya.clientescrud.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Transactional
    public EnderecoDTO cadastrar(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setUf(enderecoDTO.getUf());

        endereco = repository.save(endereco);
        return new EnderecoDTO(endereco);
    }

    @Transactional(readOnly = true)
    public List<EnderecoDTO> listar() {
        List<Endereco> enderecos = repository.findAll();
        return enderecos.stream().map(x -> new EnderecoDTO(x)).toList();
    }

    @Transactional
    public EnderecoDTO atualizar(Long id, EnderecoDTO enderecoDTO) {
        try {
            Endereco enderecoCadastrado = repository.getReferenceById(id);
            if (enderecoDTO.getLogradouro() != null) {
                enderecoCadastrado.setLogradouro(enderecoDTO.getLogradouro());
            }
            if (enderecoDTO.getBairro() != null) {
                enderecoCadastrado.setBairro(enderecoDTO.getBairro());
            }
            if (enderecoDTO.getNumero() != null) {
                enderecoCadastrado.setNumero(enderecoDTO.getNumero());
            }
            if (enderecoDTO.getCep() != null) {
                enderecoCadastrado.setCep(enderecoDTO.getCep());
            }
            if (enderecoDTO.getCidade() != null) {
                enderecoCadastrado.setCidade(enderecoDTO.getCidade());
            }
            if (enderecoDTO.getUf() != null) {
                enderecoCadastrado.setUf(enderecoDTO.getUf());
            }
            enderecoCadastrado = repository.save(enderecoCadastrado);
            return new EnderecoDTO(enderecoCadastrado);
        } catch (EntityNotFoundException e){
            throw new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + id);
        }
    }

    @Transactional
    public void excluir(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + id));
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Endereco buscarEnderecoPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + id));
    }

}
