package com.gerenciadorpedidos.demo.principal;

import com.gerenciadorpedidos.demo.models.Produto;
import com.gerenciadorpedidos.demo.repository.RepositoryCategoria;
import com.gerenciadorpedidos.demo.repository.RepositoryPedido;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;

import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private RepositoryProduto repositoryProduto;
    private RepositoryCategoria repositoryCategoria;
    private RepositoryPedido repositoryPedido;

    public Principal(RepositoryProduto repositoryProduto,
                     RepositoryCategoria repositoryCategoria,
                     RepositoryPedido repositoryPedido) {

            this.repositoryProduto = repositoryProduto;
            this.repositoryCategoria = repositoryCategoria;
            this.repositoryPedido = repositoryPedido;
    }

    public void exibe(){
        var menuEscolha = -1;
        while (menuEscolha != 0){
            System.out.println("""
                1 - Produto
                2 - Pedido
                3 - Categoria
               
                0 - Sair
                """);
            menuEscolha = scanner.nextInt();

            switch (menuEscolha){
                case 1:
                    Produtos();
                    break;

                case 2:

                    break;

                case 3:
                    break;

                default:
                    System.out.println("selecione uma opção válida!!");
            }
        }
    }
    private void Produtos(){

        System.out.println("""
                1 - Inserir Produto
                2 - Listar Produtos
                """);
        var escolhaProduto = scanner.nextInt();
        switch (escolhaProduto){
            case 1:
                inserirProduto();
                break;

            case 2:
                listarProdutos();
                break;
        }
    }
    public void inserirProduto(){
        System.out.println("Digite o nome do produto");
        var nomeProduto = scanner.nextLine();

        System.out.println("Digite o valor do Produto");
        var valorProduto = scanner.nextDouble();

        Produto produto = new Produto(nomeProduto,
                valorProduto);

        repositoryProduto.save(produto);
    }

    public void listarProdutos(){
        System.out.println(repositoryProduto.findAll());
    }
}
