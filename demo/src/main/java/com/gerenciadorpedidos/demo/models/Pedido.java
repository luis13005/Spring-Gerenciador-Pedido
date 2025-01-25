package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryPedido;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Scanner;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    private LocalDate pedidoData;

    @JoinColumn(name = "produtoId",referencedColumnName = "ProdutoId")
    private Long produtoId;

    public Pedido(LocalDate pedidoData,
                  Long produtoId){
        this.pedidoData = pedidoData;
        this.produtoId = produtoId;
    }

    public LocalDate getPedidoData() {
        return pedidoData;
    }

    public static void inserirPedido(RepositoryProduto repositoryProduto, RepositoryPedido repositoryPedido){
        Scanner leitura = new Scanner(System.in);
        System.out.println(repositoryProduto.findAll());

        System.out.println("Escolha o Pedido para adicionar ao produto");
        var prodId = leitura.nextLong();

        Pedido pedido = new Pedido(LocalDate.now(),prodId);

        repositoryPedido.save(pedido);
    }

    public static void listarPedidos(RepositoryPedido repositoryPedido){
        System.out.println(repositoryPedido.findAll());
    }
}
