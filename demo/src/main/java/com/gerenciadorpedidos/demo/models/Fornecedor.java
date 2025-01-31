package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryFornecedor;
import jakarta.persistence.*;

import java.util.List;
import java.util.Scanner;

@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fornecedorId;
    private String nome;
    @OneToMany(mappedBy = "ProdutoId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Produto> produtos;

    public Fornecedor(){
    }

    public Fornecedor(String nome) {
        this.nome = nome;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "FornecedorId: "+this.getFornecedorId()+" Nome:"+this.nome;
    }

    public static void inserirFornecedor(RepositoryFornecedor repositoryFornecedor){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do Fornecedor: ");
        var fornecedorNome = scanner.nextLine();
    }

    public static void listarFornecedor(RepositoryFornecedor repositoryFornecedor){
        System.out.println(repositoryFornecedor.findAll());
    }
}
