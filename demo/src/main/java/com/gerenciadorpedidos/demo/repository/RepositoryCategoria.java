package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryCategoria extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findByNomeContaining(String categoriaNome);
}
