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
import static com.gerenciadorpedidos.demo.models.Pedido.*;
import static com.gerenciadorpedidos.demo.models.Produto.*;

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
                    fornecedor();
                    break;

                case 2:
                    produtos();
                    break;

                case 3:
                    categoria();
                    break;

                case 4:
                    pedidos();
                    break;

                default:
                    System.out.println("selecione uma opção válida!!");
            }
        }
    }

    private void fornecedor(){
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

    private void categoria(){
        System.out.println("""
                \n1 - Inserir Categoria
                2 - Listar Categorias
                
                """);
        var escolha = scanner.nextInt();

        switch (escolha){
            case 1:
                inserirCategoria(repositoryCategoria);
                break;

            case 2:
                listarCategoria(repositoryCategoria);
                break;

            default:
                System.out.println("Insira uma Opção válida!");
        }
    }

    private void pedidos(){
        System.out.println("""
                \n1 - Inserir Peido
                2 - Listar Pedidos
                3 - Pedidos Com data de entrega
                4 - Consultar Pedidos apartir da data
                5 - Consulta datas menores que
                6 - Consulte Pedidos em um intevalo de dias
                """);
        var escolha = scanner.nextInt();

        switch (escolha){
            case 1:
                inserirPedido(repositoryProduto,repositoryPedido);
                break;
            case 2:
                listarPedidos(repositoryPedido);
                break;
            case 3:
                pedidosComDataEntrega(repositoryPedido);
                break;
            case 4:
                consultaPedidoData(repositoryPedido);
                break;
            case 5:
                consultaPedidoDataLess(repositoryPedido);
                break;
            case 6:
                consultaPeriodoData(repositoryPedido);
                break;
            default:
                System.out.println("Insira uma Opção válida!");
        }
    }

    private void produtos(){

        System.out.println("""
                1 - Inserir Produto
                2 - Listar Produtos
                3 - Consultar Produto
                4 - Consultar Produtos por Categoria
                5 - Consultar Produtos por Preço
                6 - Consulta produtos por Categoria Order by preco desc
                7 - Total Produtos por Categoria
                8 - consulta
                9 - Top 2 Produtos mais caros
                10 - Top 2 Pordutos mais caros por categoria
                
                """);
        var escolhaProduto = scanner.nextInt();

        switch (escolhaProduto){
            case 1:
                inserirProduto(repositoryProduto,repositoryFornecedor,repositoryCategoria);
                break;

            case 2:
                listarProdutos(repositoryProduto);
                break;

            case 3:
                consultarProduto(repositoryProduto);

            case 4:
                consultarProdutoCategoria(repositoryProduto);
                break;

            case 5:
                consultaProdutosPreco(repositoryProduto);
                break;

            case 6:
                consultaProdutosCategoriaPreco(repositoryProduto);
                break;

            case 7:
                contaProdutosPorCategoria(repositoryProduto);
                break;

            case 8:
                consulta(repositoryProduto);
                break;
            case 9 :
                consultaTop2(repositoryProduto);
                break;
            case 10:
                consultaTopByCategoria(repositoryProduto);
                break;
            default:
                System.out.println("Insira uma Opção válida!");
        }
    }
}
