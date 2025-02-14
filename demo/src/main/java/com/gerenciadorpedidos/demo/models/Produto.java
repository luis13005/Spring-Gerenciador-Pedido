package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryCategoria;
import com.gerenciadorpedidos.demo.repository.RepositoryFornecedor;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import jakarta.persistence.*;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ProdutoId;
    @Column(unique = true,nullable = false)
    private String nome;
    @Column(name = "valor")
    private Double preco;
    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos;
    @ManyToOne
    @JoinColumn(name = "fornecedorId")
    private Fornecedor fornecedor;
    private static Scanner leitura = new Scanner(System.in);

    public Produto(){}

    public Produto(String nome,
                   double preco,
                   Fornecedor fornecedor,
                   Categoria categoria){
            this.nome = nome;
            this.preco = preco;
            this.fornecedor = fornecedor;
            this.categoria = categoria;
    }

    public Long getProdutoId() {
        return ProdutoId;
    }

    public void setProdutoId(Long produtoId) {
        ProdutoId = produtoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {

        return "Nome: "+this.nome
                +" ;Preço: "+this.preco
                +" ;Fornecedor: "+this.fornecedor.getNome()
                +" ;Categoria: "+this.categoria.getNome();
    }

    public static void inserirProduto(RepositoryProduto repositoryProduto,
                                      RepositoryFornecedor repositoryFornecedor,
                                      RepositoryCategoria repositoryCategoria){
        try{
            System.out.println("Digite o nome do produto");
            var nomeProduto = leitura.nextLine();

            System.out.println("Digite o valor do Produto");
            var valorProduto = leitura.nextDouble();

            List<Fornecedor> fornecedores = repositoryFornecedor.findAll();
            fornecedores.forEach(System.out::println);

            System.out.println("Informe o Fornecedor: ");
            leitura.nextLine();
            var nomeFornecedor = leitura.nextLine();

            System.out.println("Fornecedor: "+nomeFornecedor);
            Optional<Fornecedor> optionalFornecedor = fornecedores.stream()
                    .filter(f -> f.getNome().equalsIgnoreCase(nomeFornecedor))
                    .findFirst();
            System.out.println("IS PRESENT: "+optionalFornecedor.isPresent());

            System.out.println("Categorias: "+repositoryCategoria.findAll());
            System.out.println("Escolha a Categoria: ");
            var categoriaNome = leitura.nextLine();
            Optional<Categoria> optionalCategoria = repositoryCategoria.findByNomeContaining(categoriaNome);

            if (optionalFornecedor.isPresent() && optionalCategoria.isPresent()){
                Fornecedor fornecedor = optionalFornecedor.get();
                Categoria categoria = optionalCategoria.get();
                System.out.println(fornecedor);
                Produto produto = new Produto(nomeProduto,
                        valorProduto,fornecedor,categoria);

                repositoryProduto.save(produto);
            }
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }catch(DataIntegrityViolationException ex){
            System.out.println("Produto já cadastrado!");
        }
    }

    public static void listarProdutos(RepositoryProduto repositoryProduto){

        System.out.println(repositoryProduto.findAll());
    }

    public static void consultarProduto(RepositoryProduto repositoryProduto){
        System.out.println("Digite o produto: ");
        var produtoNome = leitura.nextLine();

        List<Produto> produtos = repositoryProduto.findByNome(produtoNome);

        produtos.stream()
                .forEach(p -> System.out.println("Produto: "+p.getNome() ));
    }

    public static void consultarProdutoCategoria(RepositoryProduto repositoryProduto){
        System.out.println("Digite o nome da Categoria: ");
        var categoriaNome = leitura.nextLine();
        List<Produto> produtoList = repositoryProduto.findByCategoriaNomeContaining(categoriaNome);

        produtoList.stream().forEach(System.out::println);
    }

    public static void consultaProdutosPreco(RepositoryProduto repositoryProduto){
        System.out.println("Digite o Preço: ");
        var precoProduto = leitura.nextDouble();
        List<Produto> produtoList = repositoryProduto.findByPrecoGreaterThanEqual(precoProduto);
        produtoList.stream().forEach(p -> System.out.println("Produto: "+p.nome+" Preço: "+p.preco));
    }

    public static void consultaProdutosCategoriaPreco(RepositoryProduto repositoryProduto){
        System.out.println("Escolha a categoria: ");
        var categoriaNome = leitura.nextLine();

        List<Produto> produtoList = repositoryProduto
                .findByCategoriaNomeContainingOrderByPrecoDesc(categoriaNome);
        produtoList.stream().forEach(System.out::println);
    }

    public static void contaProdutosPorCategoria(RepositoryProduto repositoryProduto){
        System.out.println("Escolha a Prço: ");
        var preco = leitura.nextInt();
        Long totalProduto = repositoryProduto
                .countProdutoIdByPrecoLessThanEqual(preco);

        System.out.println("Total Produtos: "+totalProduto);
    }

    public static void consulta(RepositoryProduto repositoryProduto){
        System.out.println("Escolha a Prço ou o nome: ");
        var entrada = leitura.nextLine();
        Double preco = null;
        String nome = null;

        try {
            preco = Double.parseDouble(entrada);
        }catch (NumberFormatException e){
            nome = entrada;
        }

       List<Produto> produtoList = repositoryProduto
                .findByPrecoLessThanOrNomeContaining(preco,nome);

        produtoList.stream().forEach(System.out::println);
    }

    public static void consultaTop2(RepositoryProduto repositoryProduto){

        List<Produto> produtoList = repositoryProduto.findTop2OByOrderByPrecoDesc();

        produtoList.stream().forEach(System.out::println);
    }

    public static void consultaTopByCategoria(RepositoryProduto repositoryProduto) {
        System.out.println("Digite a categoria: ");
        var categoriaNome = leitura.nextLine();
        List<Produto> produtoList = repositoryProduto.findTop2ByCategoriaNome(categoriaNome);

        produtoList.stream().forEach(System.out::println);
    }
}
