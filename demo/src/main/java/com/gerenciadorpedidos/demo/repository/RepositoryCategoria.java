package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCategoria extends JpaRepository<Categoria,Long> {

}
