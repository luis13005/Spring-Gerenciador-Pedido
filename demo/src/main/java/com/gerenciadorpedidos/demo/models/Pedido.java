package com.gerenciadorpedidos.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;
    private LocalDate pedidoData;

    public Pedido(LocalDate pedidoData){
        this.pedidoData = pedidoData;
    }

    public LocalDate getPedidoData() {
        return pedidoData;
    }
}
