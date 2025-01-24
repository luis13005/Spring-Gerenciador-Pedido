package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProduto extends JpaRepository<Produto,Long> {

}
