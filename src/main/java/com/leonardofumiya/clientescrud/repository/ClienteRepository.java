package com.leonardofumiya.clientescrud.repository;

import com.leonardofumiya.clientescrud.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
