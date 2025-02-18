package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryCategoria;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import jakarta.persistence.*;

import java.net.PortUnreachableException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    private String nome;
//    @OneToMany(mappedBy = "ProdutoId",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Produto> produto;
    @Transient
    private final static Scanner leitura = new Scanner(System.in);

    public Categoria(){}

    public Categoria(String nome){
        this.nome = nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public List<Produto> getProduto() {
//        return produto;
//    }

//    public void setProduto(List<Produto> produto) {
//        produto.forEach(p -> p.setCategoria(this));
//        this.produto = produto;
//    }

    @Override
    public String toString() {
        return "Id: "+this.categoriaId+" Categoria: "+this.nome;
    }

    public static void inserirCategoria(RepositoryCategoria repositoryCategoria){
        System.out.println("Nome: ");
        var escolha = leitura.nextLine();
        Categoria categoria = new Categoria(escolha);

        repositoryCategoria.save(categoria);
    }

    public static void listarCategoria(RepositoryCategoria repositoryCategoria){
        System.out.println(repositoryCategoria.findAll());
    }
}
