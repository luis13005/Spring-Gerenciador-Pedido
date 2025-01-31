package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryPedido;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    private LocalDate pedidoData;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "produto_pedido",
            joinColumns = @JoinColumn(name = "PedidoId")
            ,inverseJoinColumns = @JoinColumn(name = "ProdutoId"))
    private List<Produto> produtos;

    public Pedido(){}

    public Pedido(LocalDate pedidoData){
        this.pedidoData = pedidoData;
    }

    public LocalDate getPedidoData() {
        return pedidoData;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Pedido: "+this.pedidoId+"\nData: "+this.pedidoData+"\nProduto: "+this.produtos;
    }

    public static void inserirPedido(RepositoryProduto repositoryProduto, RepositoryPedido repositoryPedido){
        Scanner leitura = new Scanner(System.in);

        List<Produto> produtos = repositoryProduto.findAll();

        produtos.stream()
                        .map(n ->
                            "Id: "+n.getProdutoId()+" Produto: "+n.getNome()
                        )
                                .forEach(System.out::println);

        System.out.println("\nDigite o nome do Produto ou Zero para sair: ");
        var prodNome = leitura.nextLine();
        while (!prodNome.equalsIgnoreCase("0")) {
                Optional<Produto> first = produtos.stream()
                        .filter(p -> p.getNome().equalsIgnoreCase(prodNome))
                        .findFirst();
                if (first.isPresent()) {
                    produtos.clear();
                    produtos.add(first.get());

                    System.out.println(produtos);
                    Pedido pedido = new Pedido(LocalDate.now());
                    pedido.setProdutos(produtos);

                    repositoryPedido.save(pedido);
                }
        }
    }

    public static void listarPedidos(RepositoryPedido repositoryPedido){
        System.out.println(repositoryPedido.findAll());
    }
}
