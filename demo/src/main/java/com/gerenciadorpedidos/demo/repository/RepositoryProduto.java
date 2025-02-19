package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryProduto extends JpaRepository<Produto,Long> {
    List<Produto> findByNome(String produtoNome);
    Produto findByNomeContaining(String produtoNome);
    List<Produto> findByCategoriaNomeContaining(String categoriaNome);
    List<Produto> findByPrecoGreaterThanEqual(Double precoProduto);
    List<Produto> findByCategoriaNomeContainingOrderByPrecoDesc(String categoriaNome);
    Long countProdutoIdByPrecoLessThanEqual(int preco);
    List<Produto> findByPrecoLessThanOrNomeContaining(Double preco, String nome);
    List<Produto> findTop2OByOrderByPrecoDesc();
    List<Produto> findTop2ByCategoriaNome(String nomeCategoria);
    @Query("SELECT p FROM Produto p where p.preco >= :preco")
    List<Produto> consultaProdutoApartirPreco(double preco);
    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC")
    List<Produto> consultaProdutoOrdenadoPreco();
    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> ConsultaProdutoOrdenadoPrecoDesc();
    @Query("SELECT p FROM Produto p where p.nome ILIKE :letraProduto%")
    List<Produto> consultaProdutoPelaPrimeiraLetra(String letraProduto);
    @Query("SELECT AVG(p.preco) FROM Produto p")
    int mediaProdutos();
    @Query("SELECT p FROM Produto p where p.preco = (SELECT MAX(p.preco) FROM Produto p)")
    Produto produtoMaisCaro();
    @Query("SELECT count(p) FROM Produto p, Categoria c WHERE c.nome ILIKE %:nomeCategoria%")
    int totalProdutosCategoria(String nomeCategoria);
    @Query("SELECT p FROM Produto p INNER JOIN Categoria c ON p.categoria = c where p.nome ILIKE %:nome% OR c.nome ILIKE %:nome%")
    List<Produto> consutaProdutoPorNomeOuCategoria(String nome);
    @Query(value = "SELECT * FROM produto order by valor desc limit 5",nativeQuery = true)
    List<Produto> consultaTopCarosNativo();
}