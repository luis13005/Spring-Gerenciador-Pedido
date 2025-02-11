package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryPedido extends JpaRepository<Pedido,Long> {
    List<Pedido> findByPedidoDataEntregaIsNotNull();
    List<Pedido> findByPedidoDataEntregaIsNull();
}
