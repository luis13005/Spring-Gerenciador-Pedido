package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPedido extends JpaRepository<Pedido,Long> {

}
