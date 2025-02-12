package com.gerenciadorpedidos.demo.repository;

import com.gerenciadorpedidos.demo.models.Pedido;
import com.gerenciadorpedidos.demo.models.Produto;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RepositoryPedido extends JpaRepository<Pedido,Long> {
    List<Pedido> findByPedidoDataEntregaIsNotNull();
    List<Pedido> findByPedidoDataEntregaIsNull();
    List<Pedido> findByPedidoDataGreaterThanEqual(LocalDate data);
    List<Pedido> findByPedidoDataLessThanEqual(LocalDate data);
    List<Pedido> findByPedidoDataBetween(LocalDate dataInicio, LocalDate dataFim);
}
