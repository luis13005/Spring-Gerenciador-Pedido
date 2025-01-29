package com.gerenciadorpedidos.demo.principal;

import com.gerenciadorpedidos.demo.models.Produto;
import com.gerenciadorpedidos.demo.repository.RepositoryCategoria;
import com.gerenciadorpedidos.demo.repository.RepositoryPedido;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;

import java.util.Scanner;

import static com.gerenciadorpedidos.demo.models.Categoria.*;
import static com.gerenciadorpedidos.demo.models.Pedido.inserirPedido;
import static com.gerenciadorpedidos.demo.models.Pedido.listarPedidos;
import static com.gerenciadorpedidos.demo.models.Produto.inserirProduto;
import static com.gerenciadorpedidos.demo.models.Produto.listarProdutos;

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
                    Pedidos();
                    break;

                case 3:
                    Categoria();
                    break;

                default:
                    System.out.println("selecione uma opção válida!!");
            }
        }
    }
    private void Categoria(){
        System.out.println("""
                \n1 - Inserir Categoria
                2 - Listar Categorias
                3 - Cadastrar Categoria no Produto 
                
                """);
        var escolha = scanner.nextInt();

        switch (escolha){
            case 1:
                inserirCategoria(repositoryCategoria);
                break;

            case 2:
                listarCategoria(repositoryCategoria);
                break;

                case 3:
                    inserirCategoriaProduto(repositoryCategoria,repositoryProduto);
                break;

            default:
                System.out.println("Insira uma Opção válida!");
        }
    }

    private void Pedidos(){
        System.out.println("""
                \n1 - Inserir Peido
                2 - Listar Pedidos
                """);
        var escolha = scanner.nextInt();

        switch (escolha){
            case 1:
                inserirPedido(repositoryProduto,repositoryPedido);
                break;

            case 2:
                listarPedidos(repositoryPedido);
                break;

            default:
                System.out.println("Insira uma Opção válida!");
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
                inserirProduto(repositoryProduto);
                break;

            case 2:
                listarProdutos(repositoryProduto);
                break;

            default:
                System.out.println("Insira uma Opção válida!");
        }
    }
}
