package com.gerenciadorpedidos.demo;

import com.gerenciadorpedidos.demo.principal.Principal;
import com.gerenciadorpedidos.demo.repository.RepositoryCategoria;
import com.gerenciadorpedidos.demo.repository.RepositoryPedido;
import com.gerenciadorpedidos.demo.repository.RepositoryProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageImpl;

@SpringBootApplication
public class GerenciadorpedidosApplication implements CommandLineRunner {
	@Autowired
	private RepositoryProduto repositoryProduto;
	@Autowired
	private RepositoryCategoria repositoryCategoria;
	@Autowired
	private RepositoryPedido repositoryPedido;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorpedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositoryProduto,repositoryCategoria,repositoryPedido);
		principal.exibe();
	}
}
