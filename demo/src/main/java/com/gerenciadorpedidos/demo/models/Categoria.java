package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryCategoria;
import jakarta.persistence.*;

import java.util.Scanner;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    private String nome;

    public Categoria(){}

    public Categoria(String nome){
        this.nome = nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Id: "+this.categoriaId+" Categoria: "+this.nome;
    }

    public static void inserirCategoria(RepositoryCategoria repositoryCategoria){
        Scanner leitura = new Scanner(System.in);
        System.out.println("Nome: ");
        var escolha = leitura.nextLine();
        Categoria categoria = new Categoria(escolha);

        repositoryCategoria.save(categoria);
    }

    public static void listarCategoria(RepositoryCategoria repositoryCategoria){
        System.out.println(repositoryCategoria.findAll());
    }
}
