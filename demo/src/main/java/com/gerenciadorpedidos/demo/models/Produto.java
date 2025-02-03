package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryFornecedor;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import jakarta.persistence.*;
import org.springframework.dao.DataIntegrityViolationException;

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

    public Produto(){}

    public Produto(String nome,
                   double preco,
                   Fornecedor fornecedor){
            this.nome = nome;
            this.preco = preco;
            this.fornecedor = fornecedor;
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
                +"\nPreço: "+this.preco
                +"\nFornecedor: "+this.fornecedor.getNome();
    }

    public static void inserirProduto(RepositoryProduto repositoryProduto,
                                      RepositoryFornecedor repositoryFornecedor){
        try{
            Scanner leitura = new Scanner(System.in);

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
            Optional<Fornecedor> first = fornecedores.stream()
                    .filter(f -> f.getNome().equalsIgnoreCase(nomeFornecedor))
                    .findFirst();
            System.out.println("IS PRESENT: "+first.isPresent());
            if (first.isPresent()){
                Fornecedor fornecedor = first.get();
                System.out.println(fornecedor);
                Produto produto = new Produto(nomeProduto,
                        valorProduto,fornecedor);

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
}
