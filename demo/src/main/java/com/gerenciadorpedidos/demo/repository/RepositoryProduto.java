package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryProduto extends JpaRepository<Produto,Long> {
    List<Produto> findByNome(String produtoNome);
    List<Produto> findByCategoriaNomeContaining(String categoriaNome);

    List<Produto> findByPrecoGreaterThanEqual(Double precoProduto);
}
