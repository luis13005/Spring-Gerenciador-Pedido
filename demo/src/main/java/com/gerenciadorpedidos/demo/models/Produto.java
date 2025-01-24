package com.gerenciadorpedidos.demo.models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

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
}
