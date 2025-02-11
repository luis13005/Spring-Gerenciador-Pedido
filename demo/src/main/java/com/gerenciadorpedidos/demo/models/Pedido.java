package com.gerenciadorpedidos.demo.models;

import com.gerenciadorpedidos.demo.repository.RepositoryPedido;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    private LocalDate pedidoData;
    private LocalDate pedidoDataEntrega;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "produto_pedido",
            joinColumns = @JoinColumn(name = "PedidoId")
            ,inverseJoinColumns = @JoinColumn(name = "ProdutoId"))
    private List<Produto> produtos;

    public Pedido(){}

    public Pedido(LocalDate pedidoData, LocalDate pedidoDataEntrega){
        this.pedidoData = pedidoData;
        this.pedidoDataEntrega = pedidoDataEntrega;
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
        produtos.clear();

        LocalDate dataEntregaFormatada = null;
        Pedido pedido = new Pedido(LocalDate.now(),dataEntregaFormatada);

        var prodNome = "-1";
        while (!prodNome.equalsIgnoreCase("0")) {
            System.out.println("\nDigite o nome do Produto ou Zero para Finalizar: ");
             prodNome = leitura.nextLine();
                Produto produto = repositoryProduto.findByNomeContaining(prodNome);
                try {

                    produtos.add(produto);

                    pedido.setProdutos(produtos);

                }catch (NullPointerException e){

                }
        }
        System.out.println("Digite a data de entrega: ");
        String dataEntrega = leitura.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataEntregaFormatada = LocalDate.parse(dataEntrega,formatter);
        pedido.setPedidoDataEntrega(dataEntregaFormatada);
        repositoryPedido.save(pedido);
    }

    public static void listarPedidos(RepositoryPedido repositoryPedido){
        System.out.println(repositoryPedido.findAll());
    }

    public LocalDate getPedidoDataEntrega() {
        return pedidoDataEntrega;
    }

    public void setPedidoDataEntrega(LocalDate pedidoDataEntrega) {
        this.pedidoDataEntrega = pedidoDataEntrega;
    }

    public static void pedidosComDataEntrega(RepositoryPedido repositoryPedido){
        List<Pedido> pedidoList = repositoryPedido.findByPedidoDataEntregaIsNotNull();
        pedidoList.stream().forEach(System.out::println);
    }
}
