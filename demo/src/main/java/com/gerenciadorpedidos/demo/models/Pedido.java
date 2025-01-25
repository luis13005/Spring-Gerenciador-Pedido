package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryPedido;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    private LocalDate pedidoData;

    @JoinColumn(name = "produtoId",referencedColumnName = "ProdutoId")
    private Long produtoId;

    public Pedido(){}

    public Pedido(LocalDate pedidoData,
                  Long produtoId){
        this.pedidoData = pedidoData;
        this.produtoId = produtoId;
    }

    public LocalDate getPedidoData() {
        return pedidoData;
    }

    @Override
    public String toString() {
        return "Pedido: "+this.pedidoId+"\nProduto: "+this.produtoId+"\nData: "+this.pedidoData;
    }

    public static void inserirPedido(RepositoryProduto repositoryProduto, RepositoryPedido repositoryPedido){
        Scanner leitura = new Scanner(System.in);

        List<Produto> produtos = repositoryProduto.findAll();

        produtos.stream()
                        .map(n ->
                            "Id: "+n.getProdutoId()+" Produto: "+n.getNome()
                        )
                                .forEach(System.out::println);

        System.out.println("\nEscolha o Pedido para adicionar ao produto");
        var prodId = leitura.nextLong();

        Pedido pedido = new Pedido(LocalDate.now(),prodId);

        repositoryPedido.save(pedido);
    }

    public static void listarPedidos(RepositoryPedido repositoryPedido){
        System.out.println(repositoryPedido.findAll());
    }
}
