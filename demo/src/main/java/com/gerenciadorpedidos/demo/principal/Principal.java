package com.gerenciadorpedidos.demo.principal;

import com.gerenciadorpedidos.demo.models.Produto;
import com.gerenciadorpedidos.demo.repository.RepositoryCategoria;
import com.gerenciadorpedidos.demo.repository.RepositoryFornecedor;
import com.gerenciadorpedidos.demo.repository.RepositoryPedido;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;

import java.util.Scanner;

import static com.gerenciadorpedidos.demo.models.Categoria.*;
import static com.gerenciadorpedidos.demo.models.Fornecedor.inserirFornecedor;
import static com.gerenciadorpedidos.demo.models.Fornecedor.listarFornecedor;
import static com.gerenciadorpedidos.demo.models.Pedido.inserirPedido;
import static com.gerenciadorpedidos.demo.models.Pedido.listarPedidos;
import static com.gerenciadorpedidos.demo.models.Produto.inserirProduto;
import static com.gerenciadorpedidos.demo.models.Produto.listarProdutos;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private RepositoryProduto repositoryProduto;
    private RepositoryCategoria repositoryCategoria;
    private RepositoryPedido repositoryPedido;
    private RepositoryFornecedor repositoryFornecedor;

    public Principal(RepositoryProduto repositoryProduto,
                     RepositoryCategoria repositoryCategoria,
                     RepositoryPedido repositoryPedido,
                     RepositoryFornecedor repositoryFornecedor) {

            this.repositoryProduto = repositoryProduto;
            this.repositoryCategoria = repositoryCategoria;
            this.repositoryPedido = repositoryPedido;
            this.repositoryFornecedor = repositoryFornecedor;
    }

    public void exibe(){
        var menuEscolha = -1;
        while (menuEscolha != 0){
            System.out.println("""
                1 - Fornecedor
                2 - Produto
                3 - Categoria
                4 - Pedido
                
               
                0 - Sair
                """);
            menuEscolha = scanner.nextInt();

            switch (menuEscolha){
                case 1:
                    Fornecedor();
                    break;

                case 2:
                    Produtos();
                    break;

                case 3:
                    Categoria();
                    break;

                case 4:
                    Pedidos();
                    break;

                default:
                    System.out.println("selecione uma opção válida!!");
            }
        }
    }

    private void Fornecedor(){
        System.out.println("""
                1 - Inserir Fornecedor
                2 - Listar Fornecedor
                """);
        var escolha = scanner.nextInt();
        switch (escolha){

            case 1:
                inserirFornecedor(repositoryFornecedor);
                break;

            case 2:
                listarFornecedor(repositoryFornecedor);
                break;
            default:
                System.out.println("Insira uma Opção válida!");
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
                inserirProduto(repositoryProduto,repositoryFornecedor);
                break;

            case 2:
                listarProdutos(repositoryProduto);
                break;

            default:
                System.out.println("Insira uma Opção válida!");
        }
    }
}
