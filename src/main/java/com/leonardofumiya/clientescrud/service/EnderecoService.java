package com.leonardofumiya.clientescrud.service;

import com.leonardofumiya.clientescrud.dto.EnderecoDTO;
import com.leonardofumiya.clientescrud.entities.Cliente;
import com.leonardofumiya.clientescrud.entities.Endereco;
import com.leonardofumiya.clientescrud.exception.EnderecoNaoEncontradoException;
import com.leonardofumiya.clientescrud.repository.ClienteRepository;
import com.leonardofumiya.clientescrud.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    private final ClienteService clienteService;

    @Transactional
    public EnderecoDTO cadastrarEndereco(EnderecoDTO enderecoDTO, Long idCliente) {
        Cliente clienteCadastrado = clienteService.buscarClientePorId(idCliente);
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setUf(enderecoDTO.getUf());

        endereco = enderecoRepository.save(endereco);

        clienteCadastrado.setIdEndereco(endereco.getIdEndereco());

        return new EnderecoDTO(endereco);
    }

    @Transactional(readOnly = true)
    public List<EnderecoDTO> listarEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(x -> new EnderecoDTO(x)).toList();
    }

    @Transactional
    public EnderecoDTO atualizarEndereco(Long idEndereco, EnderecoDTO enderecoDTO) {
        try {
            Endereco enderecoCadastrado = enderecoRepository.getReferenceById(idEndereco);
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
            enderecoCadastrado = enderecoRepository.save(enderecoCadastrado);
            return new EnderecoDTO(enderecoCadastrado);
        } catch (EntityNotFoundException e){
            throw new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + idEndereco);
        }
    }

    @Transactional
    public void excluirEndereco(Long idEndereco) {
        enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + idEndereco));
        enderecoRepository.deleteById(idEndereco);
    }

    @Transactional(readOnly = true)
    public Endereco buscarEnderecoPorId(Long idEndereco) {
        return enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new EnderecoNaoEncontradoException("Endereço não encontrado com o ID: " + idEndereco));
    }

}
