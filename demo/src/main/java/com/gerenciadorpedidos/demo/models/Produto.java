package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

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

    public Produto(String nome,
                   double preco){
            this.nome = nome;
            this.preco = preco;
    }

    public Long getProdutoId() {
        return ProdutoId;
    }

    public String getNome() {
        return nome;
    }

    public Double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Nome: "+this.nome+"\nPreço: "+this.preco;
    }

    public static void inserirProduto(RepositoryProduto repositoryProduto){
        Scanner leitura = new Scanner(System.in);

        System.out.println("Digite o nome do produto");
        var nomeProduto = leitura.nextLine();

        System.out.println("Digite o valor do Produto");
        var valorProduto = leitura.nextDouble();

        Produto produto = new Produto(nomeProduto,
                valorProduto);

        repositoryProduto.save(produto);
    }

    public static void listarProdutos(RepositoryProduto repositoryProduto){
        System.out.println(repositoryProduto.findAll());
    }
}
