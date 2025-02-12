package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryProduto extends JpaRepository<Produto,Long> {
    List<Produto> findByNome(String produtoNome);
    Produto findByNomeContaining(String produtoNome);
    List<Produto> findByCategoriaNomeContaining(String categoriaNome);
    List<Produto> findByPrecoGreaterThanEqual(Double precoProduto);
    List<Produto> findByCategoriaNomeContainingOrderByPrecoDesc(String categoriaNome);
    Long countProdutoIdByPrecoLessThanEqual(int preco);
    List<Produto> findByPrecoLessThanOrNomeContaining(int preco, String nome);
    List<Produto> findTop2OByOrderByPrecoDesc();
    List<Produto> findTop2ByCategoriaNome(String nomeCategoria);
}
