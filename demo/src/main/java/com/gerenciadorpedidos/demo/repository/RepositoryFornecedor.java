package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFornecedor extends JpaRepository<Fornecedor, Long> {
}
